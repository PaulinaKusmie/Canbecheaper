package com.kuciaapp.canbecheaperkmp.model.dto.request

data class ConfirmAccountRequest (
    var email : String,
    val code : Int
)