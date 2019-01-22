package com.github.ksarkes.weatherapp.core

import android.app.Application
import com.github.ksarkes.weatherapp.core.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import io.realm.Realm
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, presentationModule, domainModule, dataModule, networkModule, locationModule))

        AndroidThreeTen.init(this)

        Realm.init(this)
    }
}
