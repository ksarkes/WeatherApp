package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.data.repository.WeatherRepository
import org.koin.dsl.module.module

val dataModule = module {
    single { WeatherRepository(get()) }
}
