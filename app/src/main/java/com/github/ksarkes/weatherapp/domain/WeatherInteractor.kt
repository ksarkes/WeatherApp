package com.github.ksarkes.weatherapp.domain

import com.github.ksarkes.weatherapp.data.repository.WeatherRepository

class WeatherInteractor(private val weatherRepository: WeatherRepository) {

    fun getForecast(city: String) = weatherRepository.getForecast(city)

}