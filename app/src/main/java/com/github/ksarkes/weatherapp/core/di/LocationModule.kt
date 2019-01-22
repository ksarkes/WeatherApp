package com.github.ksarkes.weatherapp.core.di

import com.patloew.rxlocation.RxLocation
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val locationModule = module {
    single { RxLocation(androidContext()) }
}
