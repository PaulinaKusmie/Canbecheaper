package com.kuciaapp.canbecheaperkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform