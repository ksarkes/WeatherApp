package com.github.ksarkes.weatherapp.core.di

import android.content.Context
import android.content.SharedPreferences
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { ResourcesHelper(androidContext()) }
    single<SharedPreferences> { androidContext().getSharedPreferences("bsb3u32ini2fsdfdf", Context.MODE_PRIVATE) }
}
