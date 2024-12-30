package com.angelpr.productsshop

import android.app.Application
import com.angelpr.productsshop.di.dataModule
import com.angelpr.productsshop.di.retrofitBuilderModule
import com.angelpr.productsshop.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinInit: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@KoinInit)
            modules(retrofitBuilderModule, dataModule, viewModelModule)
        }
    }
}