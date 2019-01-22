package com.github.ksarkes.weatherapp.ui.chart

import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.util.extension.humanize
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class ChartViewModel(private val weatherInteractor: WeatherInteractor) : BaseViewModel() {

    val chartData = liveDataOf<ChartDataWrapper>()

    fun loadHistoryData(cityId: Int) {
        val history = weatherInteractor.getWeatherHistory(cityId)

        val delta = history.map { it.date }.minBy { it }!!

        val lineEntries = history.map {
            Entry((it.date - delta).toFloat() / 60, it.tempCelsius.toFloat())
        }

        val candleEntries = history.map {
            CandleEntry(
                (it.date - delta).toFloat() / 60,
                it.tempMaxCelsius.toFloat(),
                it.tempMinCelsius.toFloat(),
                it.tempMinCelsius.toFloat(),
                it.tempMaxCelsius.toFloat()
            )
        }

        chartData.postValue(ChartDataWrapper(
            lineEntries,
            candleEntries,
            IAxisValueFormatter { value, _ -> (value.toLong() * 60 + delta).humanize() }
        ))
    }
}
