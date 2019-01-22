package com.github.ksarkes.weatherapp.ui.chart

import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.util.extension.liveDataOf

class ChartViewModel(private val weatherInteractor: WeatherInteractor) : BaseViewModel() {

    val history = liveDataOf<List<HistoryWeather>>()

    fun loadHistoryData(cityId: Int) {
        weatherInteractor.getWeatherHistory(cityId).let(history::postValue)
    }
}
