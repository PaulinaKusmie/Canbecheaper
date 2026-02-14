package com.kuciaapp.canbecheaperkmp.model.api

import com.kuciaapp.canbecheaperkmp.model.dto.request.ProductPriceRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.ExtendProductPriceResponse
import com.kuciaapp.canbecheaperkmp.model.dto.response.ProductPriceResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

// shared/commonMain/network/api/ProductPriceApi.kt
class ProductPriceApi(private val client: HttpClient) {

    suspend fun getProductPriceByUser(userId: Int, typeId: Int): List<ExtendProductPriceResponse> =
        client.get("cheaper/product-prices/$userId/$typeId").body()

    suspend fun saveProductPrice(request: ProductPriceRequest): ProductPriceResponse =
        client.post("cheaper/product-prices") {
            setBody(request)
        }.body()

    suspend fun deleteProductPrice(productPriceId: Int): Boolean =
        client.delete("cheaper/product-prices/$productPriceId").body()
}