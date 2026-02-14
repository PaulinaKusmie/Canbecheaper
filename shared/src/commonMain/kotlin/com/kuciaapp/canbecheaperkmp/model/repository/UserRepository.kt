package com.kuciaapp.canbecheaperkmp.model.repository


import com.kuciaapp.canbecheaperkmp.model.api.UserApi
import com.kuciaapp.canbecheaperkmp.model.domain.User
import com.kuciaapp.canbecheaperkmp.model.dto.request.ConfirmAccountRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.ConfirmResponse
import com.kuciaapp.canbecheaperkmp.model.dto.request.LoginRequest



class UserRepository(private val userApi: UserApi) {

    suspend fun login(request: LoginRequest): ConfirmResponse? =
        try { userApi.login(request) } catch (e: Exception) { null }

    suspend fun register(user: User): ConfirmResponse? =
        try { userApi.register(user) } catch (e: Exception) { null }

    suspend fun getUser(userId: Int): User? =
        try { userApi.getUser(userId) } catch (e: Exception) { null }

    suspend fun confirmCode(request: ConfirmAccountRequest): ConfirmResponse? =
        try { userApi.confirmCode(request) } catch (e: Exception) { null }

    suspend fun deleteUser(userId: Int): Boolean =
        try { userApi.deleteUser(userId) } catch (e: Exception) { false }
}