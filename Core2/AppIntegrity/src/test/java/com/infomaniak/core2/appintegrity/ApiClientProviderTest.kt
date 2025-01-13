/*
 * Infomaniak SwissTransfer - AppIntegrity
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

import com.infomaniak.core2.appintegrity.exceptions.UnknownException
import com.infomaniak.core2.appintegrity.models.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiClientProviderTest {

    private val apiClientProvider by lazy {
        ApiClientProvider(
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            },
            userAgent = "Ktor client test"
        )
    }

    @Test
    fun apiClientProviderTest() {
        runBlocking {
            post<ApiResponse<String>>(Url("toto"), data = mapOf("toto" to 1))
        }
    }

    private suspend inline fun <reified R> post(url: Url, data: Any?): R {
        return apiClientProvider.httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.decode<R>()
    }

    private suspend inline fun <reified R> HttpResponse.decode(): R {
        return runCatching { body<R>() }.getOrElse { throw UnknownException(it) }
    }
}
