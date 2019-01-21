package com.github.ksarkes.weatherapp.ui.main

import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.ui.common.StateError
import com.github.ksarkes.weatherapp.ui.common.StateLoading
import com.github.ksarkes.weatherapp.ui.common.StateSuccess
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import io.reactivex.rxkotlin.addTo

class MainViewModel(private val weatherInteractor: WeatherInteractor) : BaseViewModel() {

    val weather = liveDataOf<ForecastWrapper>()

    fun loadWeather(city: String?) {
        state.postValue(StateLoading())

        weatherInteractor.getForecast(city ?: "")
            .map { ForecastWrapper.from(it) }
            .subscribe({
                weather.postValue(it)
                state.postValue(StateSuccess())
            }, {
                state.postValue(StateError(it))
            })
            .addTo(subs)
    }

}