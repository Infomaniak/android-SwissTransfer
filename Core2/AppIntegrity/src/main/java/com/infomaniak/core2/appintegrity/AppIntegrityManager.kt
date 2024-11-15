/*
 * Infomaniak Core2 - Android
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
package com.infomaniak.core2.appintegrity

import android.content.Context
import android.util.Base64
import android.util.Log
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.IntegrityTokenRequest
import com.google.android.play.core.integrity.StandardIntegrityManager.*
import java.util.UUID

class AppIntegrityManager(private val appContext: Context) {

    private var appIntegrityTokenProvider: StandardIntegrityTokenProvider? = null
    private val classicIntegrityTokenProvider by lazy { IntegrityManagerFactory.create(appContext) }
    private val appIntegrityRepository by lazy { AppIntegrityRepository() }

    private var challenge = ""
    private var challengeId = ""

    fun warmUpTokenProvider(appCloudNumber: Long, onFailure: (Exception) -> Unit) {
        val integrityManager = IntegrityManagerFactory.createStandard(appContext)
        integrityManager.prepareIntegrityToken(
            PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(appCloudNumber).build()
        ).addOnSuccessListener { tokenProvider ->
            appIntegrityTokenProvider = tokenProvider
            Log.i(APP_INTEGRITY_MANAGER_TAG, "warmUpTokenProvider: Success")
        }.addOnFailureListener(onFailure)
    }

    fun requestIntegrityVerdictToken(
        requestHash: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception?) -> Unit,
        onNullTokenProvider: (String) -> Unit,
    ) {
        if (appIntegrityTokenProvider == null) {
            onNullTokenProvider("Integrity token provider is null during a verdict request. This should not be possible")
        } else {
            appIntegrityTokenProvider?.request(StandardIntegrityTokenRequest.builder().setRequestHash(requestHash).build())
                ?.addOnSuccessListener { response -> onSuccess(response.token()) }
                ?.addOnFailureListener(onFailure)
        }
    }

    fun requestClassicIntegrityVerdictToken(onSuccess: (String) -> Unit, onFailure: (Exception?) -> Unit) {

        val nonce = Base64.encodeToString(challenge.toByteArray(), Base64.DEFAULT)
        Log.e("TOTO", "challenge = $challenge / nonce = $nonce")

        classicIntegrityTokenProvider.requestIntegrityToken(IntegrityTokenRequest.builder().setNonce(nonce).build())
            ?.addOnSuccessListener { response -> onSuccess(response.token()) }
            ?.addOnFailureListener(onFailure)
    }

    suspend fun getApiIntegrityVerdict(
        integrityToken: String,
        packageName: String,
        targetUrl: String,
        onSuccess: (String) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        runCatching {
            val apiResponse = appIntegrityRepository.getJwtToken(
                integrityToken = integrityToken,
                packageName = packageName,
                targetUrl = targetUrl,
                challengeId = challengeId,
            )
            apiResponse.data?.let(onSuccess)
        }.getOrElse {
            it.printStackTrace()
            onFailure(it)
        }
    }

    suspend fun getChallenge(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) = runCatching {
        generateChallengeId()
        val apiResponse = appIntegrityRepository.getChallenge(challengeId)
        Log.d(APP_INTEGRITY_MANAGER_TAG, "challengeId : $challengeId / challenge: ${apiResponse.data}")
        apiResponse.data?.let { challenge = it }
        onSuccess()
    }.getOrElse {
        it.printStackTrace()
        onFailure(it)
    }

    suspend fun callDemoRoute(mobileToken: String) {
        runCatching {
            val apiResponse = appIntegrityRepository.demo(mobileToken)
            val logMessage = if (apiResponse.isSuccess()) {
                "Success demo route response: ${apiResponse.data}"
            } else {
                "Error demo route : ${apiResponse.error?.errorCode}"
            }
            Log.d(APP_INTEGRITY_MANAGER_TAG, logMessage)
        }.getOrElse {
            it.printStackTrace()
        }
    }

    private fun generateChallengeId() {
        challengeId = UUID.randomUUID().toString()
    }

    companion object {
        const val APP_INTEGRITY_MANAGER_TAG = "App integrity manager"
    }
}
