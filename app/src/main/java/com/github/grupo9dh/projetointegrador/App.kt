package com.github.grupo9dh.projetointegrador

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import com.github.grupo9dh.projetointegrador.data.di.DataModule
import com.github.grupo9dh.projetointegrador.domain.di.DomainModule
import com.github.grupo9dh.projetointegrador.presenter.di.PresenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App() : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresenterModule.load()
    }
}