package com.kuciaapp.canbecheaperkmp.utility


    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".") && email.length > 5
    }
