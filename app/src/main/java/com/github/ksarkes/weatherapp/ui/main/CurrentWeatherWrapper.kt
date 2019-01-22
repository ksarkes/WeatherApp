package com.github.ksarkes.weatherapp.ui.main

import com.github.ksarkes.weatherapp.data.entity.Weather
import kotlin.math.roundToInt

data class CurrentWeatherWrapper(
    val city: String,
    val tempCelsius: String,
    val tempFahrenheit: String
) {

    companion object {

        fun from(data: Weather) = CurrentWeatherWrapper(
            data.cityName,
            "${data.main.temp.roundToInt()} °",
            "${(data.main.temp * 9 / 5 + 32).roundToInt()} °"
        )
    }
}
