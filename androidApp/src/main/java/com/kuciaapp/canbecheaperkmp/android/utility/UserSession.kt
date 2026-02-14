package com.kuciaapp.canbecheaperkmp.android.utility
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
val Context.userDataStore by preferencesDataStore("user_session")

class UserSessionAndroid(private val context: Context) : UserSession {

    private val USER_ID_KEY = intPreferencesKey("user_id")

    override suspend fun saveUserId(id: Int?) {
        context.userDataStore.edit { prefs ->
            id?.let { prefs[USER_ID_KEY] = it }
        }
    }

    override suspend fun getUserIdOnce(): Int? {
        return context.userDataStore.data
            .map { it[USER_ID_KEY] }
            .first()
    }

    override suspend fun clearUserId() {
        context.userDataStore.edit { it.clear() }
    }

    override fun getUserIdFlow(): Flow<Int?> {
        return context.userDataStore.data.map { it[USER_ID_KEY] }
    }
}