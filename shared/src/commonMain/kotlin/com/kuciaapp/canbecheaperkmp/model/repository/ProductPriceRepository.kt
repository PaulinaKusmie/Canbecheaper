package com.kuciaapp.canbecheaperkmp.model.repository



import com.kuciaapp.canbecheaperkmp.model.api.ProductPriceApi
import com.kuciaapp.canbecheaperkmp.model.domain.ProductPrice
import com.kuciaapp.canbecheaperkmp.model.dto.request.ProductPriceRequest
import com.kuciaapp.canbecheaperkmp.model.dto.response.ExtendProductPriceResponse
import com.kuciaapp.canbecheaperkmp.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



class ProductPriceRepository(private val productPriceApi: ProductPriceApi) {

    fun getProductPriceByUser(userId: Int, typeId: Int): Flow<List<ProductPrice>> = flow {
        try {
            emit(productPriceApi.getProductPriceByUser(userId, typeId).toDomain())
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun saveProductPrice(request: ProductPriceRequest): Flow<ProductPrice?> = flow {
        try {
            emit(productPriceApi.saveProductPrice(request).toDomain())
        } catch (e: Exception) {
            emit(null)
        }
    }

    fun deleteProductPrice(productPriceId: Int): Flow<Boolean> = flow {
        try {
            emit(productPriceApi.deleteProductPrice(productPriceId))
        } catch (e: Exception) {
            emit(false)
        }
    }
}