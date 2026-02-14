package com.kuciaapp.canbecheaperkmp.model.api


import com.kuciaapp.canbecheaperkmp.model.domain.Type
import com.kuciaapp.canbecheaperkmp.model.dto.response.TypeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

// shared/commonMain/network/api/TypeApi.kt
class TypeApi(private val client: HttpClient) {

    suspend fun getAllTypes(): List<TypeResponse> =
        client.get("cheaper/types").body()

    suspend fun getTypesByUser(userId: Int): List<TypeResponse> =
        client.get("cheaper/types/$userId").body()
}