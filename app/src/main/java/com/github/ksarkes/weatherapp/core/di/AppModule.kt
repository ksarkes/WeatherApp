package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { ResourcesHelper(androidContext()) }
}
