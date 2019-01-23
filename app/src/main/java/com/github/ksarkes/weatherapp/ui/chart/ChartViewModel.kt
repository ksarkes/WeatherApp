package com.github.ksarkes.weatherapp.ui.chart

import com.github.ksarkes.weatherapp.domain.SettingsInteractor
import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.util.extension.humanize
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import com.github.ksarkes.weatherapp.util.extension.toFahrenheit
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter

class ChartViewModel(
    private val weatherInteractor: WeatherInteractor,
    private val settingsInteractor: SettingsInteractor
) : BaseViewModel() {

    val chartData = liveDataOf<ChartDataWrapper>()

    fun loadHistoryData(cityId: Int) {
        val history = weatherInteractor.getWeatherHistory(cityId)

        val minRange = history
            .asSequence()
            .map { it.date }
            .sorted()
            .zipWithNext()
            .minBy { it.second - it.first }!!
            .let { it.second - it.first }

        val delta = history.map { it.date }.min()!!

        val lineEntries = history.map {
            Entry((it.date - delta).toFloat() / minRange, it.tempCelsius.unitize())
        }

        val candleEntries = history.map {
            CandleEntry(
                (it.date - delta).toFloat() / minRange,
                it.tempMaxCelsius.unitize(),
                it.tempMinCelsius.unitize(),
                it.tempMinCelsius.unitize(),
                it.tempMaxCelsius.unitize()
            )
        }

        chartData.postValue(
            ChartDataWrapper(
                lineEntries,
                candleEntries,
                IValueFormatter { value, _, _, _ -> "%.2f".format(value) },
                IAxisValueFormatter { value, _ -> ((value * minRange).toLong() + delta).humanize() },
                settingsInteractor.currentUnitsName
            )
        )
    }

    private fun Double.unitize() = (if (settingsInteractor.isCelsius) this else this.toFahrenheit()).toFloat()
}
