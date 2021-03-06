package com.github.ksarkes.weatherapp.data.repository

import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.data.entity.Weather
import com.github.ksarkes.weatherapp.data.net.Api
import com.github.ksarkes.weatherapp.util.extension.asRetrofitBody
import com.vicpin.krealmextensions.*

class WeatherRepository(private val api: Api) {

    fun getWeather(city: String) = api
        .getForecast(city)
        .asRetrofitBody()

    fun getWeather(lat: Double, lon: Double) = api
        .getForecast(lat, lon)
        .asRetrofitBody()

    fun saveWeather(weather: Weather, requestTime: Long) {
        HistoryWeather(
            weather.cityId, weather.cityName, weather.main.temp,
            weather.main.tempMin, weather.main.tempMax, weather.date, requestTime
        ).save()
    }

    fun getWeatherHistory(cityId: Int) = HistoryWeather().query { equalTo("cityId", cityId) }

    fun getLastHistoryWeather() = HistoryWeather().queryAll().maxBy { it.requestTime }

    fun getWeatherHistoryUpdates() = HistoryWeather().queryAllAsFlowable()
}