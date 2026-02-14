package com.kuciaapp.canbecheaperkmp.model.api

import com.kuciaapp.canbecheaperkmp.model.domain.Product
import com.kuciaapp.canbecheaperkmp.model.dto.request.ProductRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody


class ProductApi(private val client: HttpClient) {

    suspend fun saveProduct(request: ProductRequest): ProductResponse =
        client.post("cheaper/products") {
            setBody(request)
        }.body()
}