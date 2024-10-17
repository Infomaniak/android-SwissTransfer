/*
 * Infomaniak SwissTransfer - Android
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
package com.infomaniak.swisstransfer.ui.utils

import android.app.Application
import android.util.Log
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction
import com.google.android.recaptcha.RecaptchaClient
import com.infomaniak.swisstransfer.BuildConfig
import com.infomaniak.swisstransfer.ui.MainApplication
import javax.inject.Inject

/**
 * A class responsible for handling reCAPTCHA verification for uploads.
 *
 * This class uses the Google reCAPTCHA API to verify user actions and prevent abuse.
 * It initializes a reCAPTCHA client and provides a method to fetch a reCAPTCHA code.
 *
 * @property application The application context.
 */
class UploadRecaptcha @Inject constructor(private val application: MainApplication) {

    private var client: RecaptchaClient? = null

    /**
     * Initializes the reCAPTCHA client.
     *
     * This method should be called before attempting to fetch a reCAPTCHA code.
     * It fetches the reCAPTCHA client using the provided API site key.
     * If an error occurs during initialization, it logs the error message.
     * It MUST be called in the MainApplication.
     */
    suspend fun initializeClient() {
        runCatching {
            client = Recaptcha.fetchClient(application, BuildConfig.RECAPTCHA_API_SITE_KEY)
        }.onFailure {
            Log.e("Recaptcha", "Getting Recaptcha client failed with an exception", it)
        }
    }

    /**
     * Fetches a reCAPTCHA code.
     *
     * This method executes the reCAPTCHA challenge and retrieves the code.
     * The code is then passed to the provided callback function.
     *
     * @param callback A function that receives the reCAPTCHA code as a string.
     *                 The code may be null if an error occurred.
     */
    suspend fun fetchCode(callback: (String?) -> Unit) {
        callback(client?.execute(RecaptchaAction.LOGIN)?.getOrNull())
    }

    companion object {
        private const val TAG = "UploadRecaptcha"
    }
}
