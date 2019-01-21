package com.github.ksarkes.weatherapp.data.repository

import com.github.ksarkes.weatherapp.data.net.Api
import com.github.ksarkes.weatherapp.util.extension.asRetrofitBody

class WeatherRepository(private val api: Api) {

    fun getForecast(city: String) = api
        .getForecast(city)
        .asRetrofitBody()

}