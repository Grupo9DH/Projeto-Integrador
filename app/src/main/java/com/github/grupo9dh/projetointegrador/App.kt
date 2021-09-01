package com.github.grupo9dh.projetointegrador

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App() : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }
    }
}