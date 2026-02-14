// androidApp/MyApplication.kt
package com.kuciaapp.canbecheaperkmp.android

import android.app.Application
import com.kuciaapp.canbecheaperkmp.android.di.androidModule
import com.kuciaapp.canbecheaperkmp.android.utility.UserSessionAndroid
import com.kuciaapp.canbecheaperkmp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                androidModule,
                module {
                    single { UserSessionAndroid(androidContext()) }
                }
            )
        }
    }
}