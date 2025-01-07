/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.core2.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.infomaniak.core2.sentry.SentryLog
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class NetworkAvailability(private val context: Context, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val connectivityManager by lazy { context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    private val mutex = Mutex()

    val isNetworkAvailable: Flow<Boolean> = callbackFlow {
        val networks = mutableListOf<Network>()

        val callback = object : NetworkCallback() {

            override fun onAvailable(network: Network) {
                launch {
                    mutex.withLock {
                        networks.add(network)
                        send(element = hasAvailableNetwork(networks))
                    }
                }
            }

            override fun onLost(network: Network) {
                launch {
                    mutex.withLock {
                        networks.remove(network)
                        send(element = hasAvailableNetwork(networks))
                    }
                }
            }
        }

        launch(ioDispatcher) {
            send(getInitialNetworkAvailability(connectivityManager))
        }

        registerNetworkCallback(connectivityManager, callback, ::send)

        awaitClose { unregisterNetworkCallback(connectivityManager, callback) }
    }

    private suspend fun registerNetworkCallback(
        connectivityManager: ConnectivityManager,
        callback: NetworkCallback,
        send: suspend (Boolean) -> Unit,
    ) {
        runCatching {
            connectivityManager.registerNetworkCallback(networkRequestBuilder(), callback)
        }.onFailure { exception ->
            // Fix potential Exception thrown by ConnectivityManager on Android 11.
            // Already fixed in Android S and above.
            // https://issuetracker.google.com/issues/175055271
            SentryLog.e(TAG, "Android 11 exception", exception)
            send(false)
        }
    }

    private fun unregisterNetworkCallback(connectivityManager: ConnectivityManager, callback: NetworkCallback) {
        runCatching {
            connectivityManager.unregisterNetworkCallback(callback)
        }.onFailure { exception ->
            Sentry.captureException(exception)
        }
    }

    private fun getInitialNetworkAvailability(connectivityManager: ConnectivityManager): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork?.let(::hasInternetConnectivity) ?: false
        } else {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    private fun networkRequestBuilder(): NetworkRequest {
        return NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }.build()
    }

    private fun hasInternetConnectivity(network: Network) = runCatching {
        network.getByName(ROOT_SERVER_URL) != null
    }.getOrDefault(false)

    private suspend fun hasAvailableNetwork(networks: List<Network>) = withContext(ioDispatcher) {
        networks.any(::hasInternetConnectivity)
    }

    companion object {
        private val TAG = NetworkAvailability::class.java.simpleName
        private const val ROOT_SERVER_URL = "a.root-servers.net"
    }
}
