package com.kuciaapp.canbecheaperkmp.utility

import kotlinx.coroutines.flow.Flow

// shared/commonMain/utility/UserSession.kt
interface UserSession {
    suspend fun saveUserId(id: Int?)
    suspend fun getUserIdOnce(): Int?
    suspend fun clearUserId()
    fun getUserIdFlow(): Flow<Int?>
}