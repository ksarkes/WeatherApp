package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.data.repository.LocationRepository
import com.github.ksarkes.weatherapp.data.repository.SettingsRepository
import com.github.ksarkes.weatherapp.data.repository.WeatherRepository
import org.koin.dsl.module.module

val dataModule = module {
    single { WeatherRepository(get()) }
    single { LocationRepository(get()) }
    single { SettingsRepository(get(), get()) }
}
