package com.fpadilha90.assetsinfo

import android.app.Application
import com.fpadilha90.assetsinfo.di.AppInject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(AppInject.modules())
        }
    }
}