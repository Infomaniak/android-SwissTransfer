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
import com.infomaniak.core2.appintegrity.exceptions.NetworkException
import com.infomaniak.sentry.SentryLog
import io.sentry.Sentry
import io.sentry.SentryLevel
import java.util.UUID

/**
 * Manager used to verify that the device used is real and doesn't have integrity problems
 *
 * There is 2 types of Request:
 * - the standard request ([requestIntegrityVerdictToken]) that need a warm-up first ([warmUpTokenProvider])
 * - the classic request ([requestClassicIntegrityVerdictToken]) that need additional API checks
 */
class AppIntegrityManager(private val appContext: Context, userAgent: String) {

    private var appIntegrityTokenProvider: StandardIntegrityTokenProvider? = null
    private val classicIntegrityTokenProvider by lazy { IntegrityManagerFactory.create(appContext) }
    private val appIntegrityRepository by lazy { AppIntegrityRepository(userAgent) }

    private var challenge = ""
    private var challengeId = ""

    /**
     * This function is needed in case of standard verdict request by [requestIntegrityVerdictToken].
     * It must be called once at the initialisation because it can take a long time (up to several minutes)
     */
    fun warmUpTokenProvider(appCloudNumber: Long, onFailure: () -> Unit) {
        val integrityManager = IntegrityManagerFactory.createStandard(appContext)
        integrityManager.prepareIntegrityToken(
            PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(appCloudNumber).build()
        ).addOnSuccessListener { tokenProvider ->
            appIntegrityTokenProvider = tokenProvider
            SentryLog.i(APP_INTEGRITY_MANAGER_TAG, "warmUpTokenProvider: Success")
        }.addOnFailureListener { manageException(it, "Error during warmup", onFailure) }
    }

    /**
     * Standard verdict request for Integrity token
     * It should protect automatically from replay attack, but for now this protection seemed to not be working
     */
    fun requestIntegrityVerdictToken(
        requestHash: String,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit,
        onNullTokenProvider: (String) -> Unit,
    ) {
        if (appIntegrityTokenProvider == null) {
            onNullTokenProvider("Integrity token provider is null during a verdict request. This should not be possible")
        } else {
            appIntegrityTokenProvider?.request(StandardIntegrityTokenRequest.builder().setRequestHash(requestHash).build())
                ?.addOnSuccessListener { response -> onSuccess(response.token()) }
                ?.addOnFailureListener { manageException(it, "Error when requiring a standard integrity token", onFailure) }
        }
    }

    /**
     * Classic verdict request for Integrity token
     *
     * This doesn't automatically protect from replay attack, thus the use of challenge/challengeId pair with our API to add this
     * layer of protection.
     */
    fun requestClassicIntegrityVerdictToken(onSuccess: (String) -> Unit, onFailure: () -> Unit) {

        // You can comment this if you want to test the App Integrity (also see getJwtToken in AppIntegrityRepository)
        if (BuildConfig.DEBUG) {
            onSuccess("Basic app integrity token")
            return
        }

        val nonce = Base64.encodeToString(challenge.toByteArray(), Base64.DEFAULT)

        classicIntegrityTokenProvider.requestIntegrityToken(IntegrityTokenRequest.builder().setNonce(nonce).build())
            ?.addOnSuccessListener { response -> onSuccess(response.token()) }
            ?.addOnFailureListener { manageException(it, "Error when requiring a classic integrity token", onFailure) }
    }

    suspend fun getChallenge(onSuccess: () -> Unit, onFailure: () -> Unit) = runCatching {
        generateChallengeId()
        val apiResponse = appIntegrityRepository.getChallenge(challengeId)
        SentryLog.d(
            tag = APP_INTEGRITY_MANAGER_TAG,
            msg = "challengeId hash : ${challengeId.hashCode()} / challenge hash: ${apiResponse.data.hashCode()}",
        )
        apiResponse.data?.let { challenge = it }
        onSuccess()
    }.getOrElse {
        manageException(it, "Error fetching challenge", onFailure)
    }

    suspend fun getApiIntegrityVerdict(
        integrityToken: String,
        packageName: String,
        targetUrl: String,
        onSuccess: (String) -> Unit,
        onFailure: () -> Unit,
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
            manageException(it, "Error during Integrity check by API", onFailure)
        }
    }

    /**
     *  Only used to test App Integrity in Apps before their real backend implementation
     */
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

    private fun manageException(exception: Throwable, errorMessage: String, onFailure: () -> Unit) {
        if (exception !is NetworkException) {
            Sentry.captureMessage(errorMessage, SentryLevel.ERROR) { scope ->
                scope.setTag("exception", exception.message.toString())
                scope.setExtra("stacktrace", exception.printStackTrace().toString())
            }
        }
        exception.printStackTrace()
        onFailure()
    }

    companion object {
        const val APP_INTEGRITY_MANAGER_TAG = "App integrity manager"
        const val ATTESTATION_TOKEN_HEADER = "Ik-mobile-token"
    }
}
