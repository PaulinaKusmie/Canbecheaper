package com.kuciaapp.canbecheaperkmp.model.dto.request

data class ProductPriceRequest(
    val cheaperProductId: Int,
    val cheaperPriceId: Int,
    val cheaperTypeId: Int,
    val userId: Int,
    val price: Double? = null,
    val name: String? = null
)