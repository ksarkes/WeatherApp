package com.github.ksarkes.weatherapp.ui.main.history

import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.util.extension.humanize
import com.github.ksarkes.weatherapp.util.extension.toFahrenheit
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.math.roundToInt

data class WeatherHistoryItemWrapper(
    val cityId: Int,
    val cityName: String,
    val titleCelsius: String,
    val titleFahrenheit: String,
    val date: String,
    val hasChart: Boolean
) {

    companion object : KoinComponent {

        private val res: ResourcesHelper by inject()

        fun from(data: HistoryWeather, hasChart: Boolean) = WeatherHistoryItemWrapper(
            cityId = data.cityId,
            cityName = data.cityName,
            titleCelsius = res.getString(R.string.city_title_celsius, data.cityName, data.tempCelsius.roundToInt()),
            titleFahrenheit = res.getString(
                R.string.city_title_fahrenheit,
                data.cityName,
                data.tempCelsius.toFahrenheit().roundToInt()
            ),
            date = data.date.humanize(),
            hasChart = hasChart
        )
    }
}
