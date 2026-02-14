package com.kuciaapp.canbecheaperkmp.model.dto.response

data class ProductPriceResponse(
    val id: Int,
    val productId: Int,
    val priceId: Int,
    val typeId: Int,
    val userId: Int,
    val createdAt: String
)