package com.github.ksarkes.weatherapp.ui.main

import android.location.Location
import com.github.ksarkes.weatherapp.domain.LocationInteractor
import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.ui.common.StateError
import com.github.ksarkes.weatherapp.ui.common.StateLoading
import com.github.ksarkes.weatherapp.ui.common.StateSuccess
import com.github.ksarkes.weatherapp.ui.main.history.WeatherHistoryItemWrapper
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import io.reactivex.rxkotlin.addTo

class MainViewModel(
    private val weatherInteractor: WeatherInteractor,
    private val locationInteractor: LocationInteractor
) : BaseViewModel() {

    val weather = liveDataOf<CurrentWeatherWrapper>()

    val history = liveDataOf<List<WeatherHistoryItemWrapper>>()

    init {
        weatherInteractor.getWeatherHistory()
            .map { history -> history.map { WeatherHistoryItemWrapper.from(it, true) } }
            .subscribe({
                history.postValue(it)
            }, {
                it.printStackTrace()
            })
            .addTo(subs)
    }

    fun loadWeather(city: String?) {
        state.postValue(StateLoading())

        weatherInteractor.getCurrentWeather(city ?: "")
            .map { CurrentWeatherWrapper.from(it) }
            .subscribe({
                weather.postValue(it)
                state.postValue(StateSuccess())
            }, {
                state.postValue(StateError(it))
            })
            .addTo(subs)
    }

    fun loadHomeWeather() {
        locationInteractor.getLocation()
            .subscribe(::loadWeather) { state.postValue(StateError(it)) }
            .addTo(subs)
    }

    private fun loadWeather(loc: Location) {
        state.postValue(StateLoading())

        weatherInteractor.getCurrentWeather(loc)
            .map { CurrentWeatherWrapper.from(it) }
            .subscribe({
                weather.postValue(it)
                state.postValue(StateSuccess())
            }, {
                state.postValue(StateError(it))
            })
            .addTo(subs)
    }
}