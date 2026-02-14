package com.kuciaapp.canbecheaperkmp.android.di

import com.kuciaapp.canbecheaperkmp.android.utility.UserSessionAndroid
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import com.kuciaapp.canbecheaperkmp.viewmodel.ConfigurationViewModel
import com.kuciaapp.canbecheaperkmp.viewmodel.LoginViewModel
import com.kuciaapp.canbecheaperkmp.viewmodel.ProductPriceViewModel
import com.kuciaapp.canbecheaperkmp.viewmodel.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val androidModule = module {
    single<UserSession> { UserSessionAndroid(androidContext()) }
    viewModel{ LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get())}
    viewModel { ConfigurationViewModel(get(), get()) }
    viewModel { ProductPriceViewModel(get(), get(), get()) }
}

