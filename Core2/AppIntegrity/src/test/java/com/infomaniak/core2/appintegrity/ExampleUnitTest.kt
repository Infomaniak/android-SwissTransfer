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
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val apiClientProvider by lazy {
        ApiClientProvider(
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun toto() {
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
