package com.kuciaapp.canbecheaperkmp.model.repository



import com.kuciaapp.canbecheaperkmp.model.api.TypeApi
import com.kuciaapp.canbecheaperkmp.model.domain.Type
import com.kuciaapp.canbecheaperkmp.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// shared/commonMain/model/repository/TypeRepository.kt
class TypeRepository(private val typeApi: TypeApi) {

    fun getAllTypes(): Flow<List<Type>> = flow {
        try {
            emit(typeApi.getAllTypes().toDomain())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getTypesByUser(userId: Int): Flow<List<Type>> = flow {
        try {
            emit(typeApi.getTypesByUser(userId).toDomain())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}