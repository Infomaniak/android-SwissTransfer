/*
 * Infomaniak Core - Android
 * Copyright (C) 2024 Infomaniak Network SA
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
package com.infomaniak.appintegrity

import android.content.Context
import android.util.Log
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.StandardIntegrityManager.*
import com.infomaniak.appintegrity.exceptions.ApiException
import kotlinx.coroutines.coroutineScope

class AppIntegrityManager(private val packageName: String) {

    private var appIntegrityTokenProvider: StandardIntegrityTokenProvider? = null

    fun warmUpTokenProvider(appContext: Context, appCloudNumber: Long, onFailure: (Exception) -> Unit) {
        val integrityManager = IntegrityManagerFactory.createStandard(appContext)
        integrityManager.prepareIntegrityToken(
            PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(appCloudNumber).build()
        ).addOnSuccessListener { tokenProvider ->
            appIntegrityTokenProvider = tokenProvider
            Log.e("TOTO", "warmUpTokenProvider: Success")
        }.addOnFailureListener(onFailure)
    }

    suspend fun requestIntegrityVerdictToken(
        requestHash: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception?) -> Unit,
        onNullTokenProvider: (String) -> Unit,
    ) = coroutineScope {
        if (appIntegrityTokenProvider == null) {
            onNullTokenProvider("Integrity token provider is null during a verdict request. This should not be possible")
        } else {
            Log.e("TOTO", "requestIntegrityVerdictToken: b")
            appIntegrityTokenProvider?.request(StandardIntegrityTokenRequest.builder().setRequestHash(requestHash).build())
                ?.addOnSuccessListener { response ->
                    onSuccess(response.token())
                }
                ?.addOnFailureListener(onFailure)
        }
    }

    suspend fun requestApiJwtToken(integrityToken: String, targetUrl: String): String? = runCatching {
        Log.e("TOTO", "requestApiJwtToken: successful integrity call token = $integrityToken")
        AppIntegrityRepository.getJwtToken(integrityToken, packageName, targetUrl).data?.let { callDemoRoute(it) }
    }.getOrElse { exception ->
        exception.printStackTrace()
        if (exception is ApiException) {
            when (exception.message) {
                "invalid_attestation" -> "Integrity is invalid"
                else -> "unknown ApiError"
            }
        } else {
            null
        }
    }

    private suspend fun callDemoRoute(mobileToken: String): String? = runCatching {
        val apiResponse = AppIntegrityRepository.demo(mobileToken)
        Log.e("TOTO", "callDemoRoute: success ${apiResponse.data}")
        apiResponse.data
    }.getOrElse { exception ->
        if (exception is ApiException) {
            when (exception.message) {
                "already_used_token" -> "The JWT token has been already used"
                "expired_token" -> "The JWT token has expired"
                "invalid_mobile_token" -> "Mobile token is missing or invalid"
                else -> "unknown ApiError"
            }
        } else {
            null
        }
    }
}
