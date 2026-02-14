package com.kuciaapp.canbecheaperkmp.model.api

import com.kuciaapp.canbecheaperkmp.model.dto.request.PriceRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.PriceResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody


// shared/commonMain/network/api/PriceApi.kt
class PriceApi(private val client: HttpClient) {

    suspend fun savePrice(request: PriceRequest): PriceResponse =
        client.post("cheaper/prices") {
            setBody(request)
        }.body()

    suspend fun updatePrice(id: Int, price: Double): PriceResponse =
        client.put("cheaper/prices/$id/$price").body()
}