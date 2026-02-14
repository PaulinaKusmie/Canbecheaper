package com.kuciaapp.canbecheaperkmp.model.repository


import com.kuciaapp.canbecheaperkmp.model.api.PriceApi
import com.kuciaapp.canbecheaperkmp.model.domain.Price
import com.kuciaapp.canbecheaperkmp.model.dto.request.PriceRequest
import com.kuciaapp.canbecheaperkmp.model.toDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PriceRepository(private val priceApi: PriceApi) {

    fun savePrice(request: PriceRequest): Flow<Price?> = flow {
        try {
            emit(priceApi.savePrice(request).toDomain())
        } catch (e: Exception) {
            emit(null)
        }
    }

    fun updatePrice(priceId: Int, price: Double): Flow<Price?> = flow {
        try {
            emit(priceApi.updatePrice(priceId, price).toDomain())
        } catch (e: Exception) {
            emit(null)
        }
    }
}