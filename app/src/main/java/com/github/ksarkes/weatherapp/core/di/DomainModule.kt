package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.domain.LocationInteractor
import com.github.ksarkes.weatherapp.domain.SettingsInteractor
import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import org.koin.dsl.module.module

val domainModule = module {
    single { WeatherInteractor(get()) }
    single { LocationInteractor(get()) }
    single { SettingsInteractor(get()) }
}
