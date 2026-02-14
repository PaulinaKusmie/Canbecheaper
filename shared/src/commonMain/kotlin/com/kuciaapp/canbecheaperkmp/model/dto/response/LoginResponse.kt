package com.kuciaapp.canbecheaperkmp.model.dto.response

data class ConfirmResponse(
    val success: Boolean,
    val message: String,
    val id: Int? = null  )