package com.github.ksarkes.weatherapp.domain

import android.location.Location
import com.github.ksarkes.weatherapp.data.repository.WeatherRepository

class WeatherInteractor(private val weatherRepository: WeatherRepository) {

    fun getCurrentWeather(city: String) = weatherRepository
        .getWeather(city)
        .doOnSuccess { weatherRepository.saveWeather(it, System.currentTimeMillis()) }

    fun getCurrentWeather(loc: Location) = weatherRepository
        .getWeather(loc.latitude, loc.longitude)
        .doOnSuccess { weatherRepository.saveWeather(it, System.currentTimeMillis()) }

    fun getWeatherHistory(cityId: Int) = weatherRepository.getWeatherHistory(cityId)

    fun observeWeatherHistory() = weatherRepository.getWeatherHistory()
}