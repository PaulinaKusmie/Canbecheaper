package com.kuciaapp.canbecheaperkmp.model.repository



import com.kuciaapp.canbecheaperkmp.model.api.ProductApi
import com.kuciaapp.canbecheaperkmp.model.domain.Product
import com.kuciaapp.canbecheaperkmp.model.dto.request.ProductRequest
import com.kuciaapp.canbecheaperkmp.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



class ProductRepository(private val productApi: ProductApi) {

    fun saveProduct(request: ProductRequest): Flow<Product?> = flow {
        try {
            emit(productApi.saveProduct(request).toDomain())
        } catch (e: Exception) {
            emit(null)
        }
    }
}