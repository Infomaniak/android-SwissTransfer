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
import javax.inject.Inject

class Recaptcha @Inject constructor(private val application: Application) {

    private var client: RecaptchaClient? = null

    suspend fun initializeClient() {
        runCatching {
            client = Recaptcha.fetchClient(application, BuildConfig.RECAPTCHA_API_SITE_KEY)
        }.onFailure {
            Log.e("Recaptcha", "Getting Recaptcha client failed with an exception: $it")
        }
    }

    suspend fun fetchCode(callback: (String?) -> Unit) {
        callback(client?.execute(RecaptchaAction.LOGIN)?.getOrNull())
    }

    companion object {
        private const val TAG = "Recaptcha"
    }
}
