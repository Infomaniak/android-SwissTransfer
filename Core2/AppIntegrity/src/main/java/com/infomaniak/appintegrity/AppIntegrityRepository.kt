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
package com.infomaniak.appintegrity

import com.infomaniak.appintegrity.exceptions.UnknownException
import com.infomaniak.appintegrity.models.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.contentType

object AppIntegrityRepository {

    private val apiClientProvider by lazy { ApiClientProvider() }

    suspend fun getChallenge(challengeId: String): ApiResponse<String> {
        val body = mapOf("challenge_id" to challengeId)
        return post<ApiResponse<String>>(url = Url(AppIntegrityRoutes.requestChallenge), data = body)
    }

    suspend fun getJwtToken(
        integrityToken: String,
        packageName: String,
        targetUrl: String,
        challengeId: String,
    ): ApiResponse<String> {
        val body = mapOf(
            "token" to integrityToken,
            "package_name" to packageName,
            "target_url" to targetUrl,
            "challenge_id" to challengeId,
        )

        return post<ApiResponse<String>>(url = Url(AppIntegrityRoutes.requestApiIntegrityCheck), data = body)
    }

    suspend fun demo(mobileToken: String): ApiResponse<String> {
        val body = mapOf("mobile_token" to mobileToken)
        return post<ApiResponse<String>>(url = Url(AppIntegrityRoutes.demo), data = body)
    }

    private suspend inline fun <reified R> post(url: Url, data: Any?, httpClient: HttpClient = apiClientProvider.httpClient): R {
        return httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.decode<R>()
    }


    private suspend inline fun <reified R> HttpResponse.decode(): R {
        return runCatching { body<R>() }.getOrElse { throw UnknownException(it) }
    }
}
