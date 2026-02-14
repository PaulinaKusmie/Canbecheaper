
package com.kuciaapp.canbecheaperkmp.di

import com.kuciaapp.canbecheaperkmp.network.createHttpClient
import com.kuciaapp.canbecheaperkmp.model.api.*
import com.kuciaapp.canbecheaperkmp.model.repository.*

import com.kuciaapp.canbecheaperkmp.viewmodel.*
import org.koin.dsl.module

val appModule = module {

    // HttpClient
    single { createHttpClient() }

    // APIs
    single { UserApi(get()) }
    single { PriceApi(get()) }
    single { ProductApi(get()) }
    single { ProductPriceApi(get()) }
    single { TypeApi(get()) }

    // Repositories
    single { UserRepository(get()) }
    single { PriceRepository(get()) }
    single { ProductRepository(get()) }
    single { ProductPriceRepository(get()) }
    single { TypeRepository(get()) }

    // ViewModels

}