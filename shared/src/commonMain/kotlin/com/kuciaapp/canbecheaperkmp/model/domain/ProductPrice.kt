package com.kuciaapp.canbecheaperkmp.model.domain

data class ProductPrice(
    val id: Int,
    val priceId: Int,
    val productId: Int,
    val typeId: Int,
    val userId: Int,
    val createdAt: String? = null,
    val price: Double? = null,
    val name: String? = null
)