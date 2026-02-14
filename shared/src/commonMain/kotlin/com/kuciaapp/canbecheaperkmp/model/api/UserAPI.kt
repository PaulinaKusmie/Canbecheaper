package com.kuciaapp.canbecheaperkmp.model.api

import com.kuciaapp.canbecheaperkmp.model.domain.User
import com.kuciaapp.canbecheaperkmp.model.dto.request.ConfirmAccountRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.ConfirmResponse
import com.kuciaapp.canbecheaperkmp.model.dto.request.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody



class UserApi(private val client: HttpClient) {

    suspend fun login(request: LoginRequest): ConfirmResponse =
        client.post("users/login") {
            setBody(request)
        }.body()

    suspend fun register(user: User): ConfirmResponse =
        client.post("users/register") {
            setBody(user)
        }.body()

    suspend fun getUser(userId: Int): User =
        client.get("user/$userId").body()

    suspend fun confirmCode(request: ConfirmAccountRequest): ConfirmResponse =
        client.post("users/confirmCode") {
            setBody(request)
        }.body()

    suspend fun deleteUser(userId: Int): Boolean =
        client.post("users/deleteUser/$userId").body()
}
