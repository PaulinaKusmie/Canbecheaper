// shared/commonMain/network/HttpClientFactory.kt
package com.kuciaapp.canbecheaperkmp.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val BASE_URL = "http://srv18.mikr.us:20230/"

fun createHttpClient(): HttpClient = HttpClient {

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            //var serializeNulls = true // masz serializeNulls w Gson więc zostawiamy
        })
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.BODY
    }

    defaultRequest {
        url(BASE_URL)
        contentType(ContentType.Application.Json)
    }
}