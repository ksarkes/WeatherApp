package com.github.ksarkes.weatherapp.ui.main.history

import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.util.DateFormat
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.math.roundToInt

data class WeatherHistoryItemWrapper(
    val cityId: Int,
    val cityName: String,
    val temp: String,
    val date: String,
    val hasChart: Boolean
) {

    companion object {

        fun from(data: HistoryWeather, hasChart: Boolean) = WeatherHistoryItemWrapper(
            cityId = data.cityId,
            cityName = data.cityName,
            temp = data.tempCelsius.roundToInt().toString(),
            date = getDate(data.date),
            hasChart = hasChart
        )

        private fun getDate(unixTime: Long) = LocalDateTime
            .ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.systemDefault())
            .format(DateFormat.DEFAULT_DATE_TIME)
    }
}
