package com.github.ksarkes.weatherapp.ui.main

import com.github.ksarkes.weatherapp.R
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
            background = res.getColor(
                with(data.main.temp) {
                    when {
                        this < 10 -> R.color.primary
                        this > 25 -> R.color.deepOrange
                        else -> R.color.accent
                    }
                }
            )
        )
    }
}
