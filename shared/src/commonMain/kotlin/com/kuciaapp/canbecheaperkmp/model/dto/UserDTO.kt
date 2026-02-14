package com.kuciaapp.canbecheaperkmp.model.dto

import kotlinx.serialization.Serializable

@Serializable
data  class UserDTO (val id: Int,
val email: String,
val name: String,
val age: Int,
val password: String,
val created: String?)