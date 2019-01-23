package com.github.ksarkes.weatherapp.ui.main

import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.data.entity.Weather
import com.github.ksarkes.weatherapp.util.extension.toFahrenheit
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.math.roundToInt

data class CurrentWeatherWrapper(
    val city: String,
    val tempCelsius: String,
    val tempFahrenheit: String,
    val background: Int
) {

    companion object : KoinComponent {

        val res: ResourcesHelper by inject()

        fun from(data: Weather) = CurrentWeatherWrapper(
            city = data.cityName,
            tempCelsius = "${data.main.temp.roundToInt()} °",
            tempFahrenheit = "${data.main.temp.toFahrenheit().roundToInt()} °",
            background = getBackground(data.main.temp)
        )

        fun from(data: HistoryWeather) = CurrentWeatherWrapper(
            city = data.cityName,
            tempCelsius = "${data.tempCelsius.roundToInt()} °",
            tempFahrenheit = "${data.tempCelsius.toFahrenheit().roundToInt()} °",
            background = getBackground(data.tempCelsius)
        )

        private fun getBackground(temp: Double) = res.getColor(
            with(temp) {
                when {
                    this < 10 -> R.color.primary
                    this > 25 -> R.color.deepOrange
                    else -> R.color.accent
                }
            }
        )
    }
}
