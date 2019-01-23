package com.github.ksarkes.weatherapp.ui.main

import android.location.Location
import com.github.ksarkes.weatherapp.domain.LocationInteractor
import com.github.ksarkes.weatherapp.domain.SettingsInteractor
import com.github.ksarkes.weatherapp.domain.WeatherInteractor
import com.github.ksarkes.weatherapp.ui.common.BaseViewModel
import com.github.ksarkes.weatherapp.ui.common.StateError
import com.github.ksarkes.weatherapp.ui.common.StateLoading
import com.github.ksarkes.weatherapp.ui.common.StateSuccess
import com.github.ksarkes.weatherapp.ui.main.history.WeatherHistoryItemWrapper
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainViewModel(
    private val weatherInteractor: WeatherInteractor,
    private val locationInteractor: LocationInteractor,
    private val settingsInteractor: SettingsInteractor
) : BaseViewModel() {

    val weather = liveDataOf<CurrentWeatherWrapper>()

    val history = liveDataOf<List<WeatherHistoryItemWrapper>>()

    val isCelsius = liveDataOf(settingsInteractor.isCelsius)

    private val weatherLoadingSubs = CompositeDisposable()

    init {
        weatherInteractor.observeWeatherHistory()
            .map { all ->
                all.groupBy { it.cityId }.values
                    .map { cityGroup -> cityGroup.maxBy { it.date }!! to cityGroup.size }
                    .sortedByDescending { it.first.requestTime }
            }
            .map { cities -> cities.map { WeatherHistoryItemWrapper.from(it.first, it.second > 1) } }
            .subscribe({
                history.postValue(it)
            }, {
                it.printStackTrace()
            })
            .addTo(subs)
    }

    fun loadWeather(city: String?) {
        loadWeather(WeatherRequestType.CityName(city ?: ""))
    }

    fun loadHomeWeather() {
        state.postValue(StateLoading())

        locationInteractor.getLocation()
            .subscribe(::loadWeather) { state.postValue(StateError(it)) }
            .addTo(weatherLoadingSubs)
    }

    fun checkCelsius(checked: Boolean) {
        settingsInteractor.isCelsius = checked
    }

    private fun loadWeather(loc: Location) {
        loadWeather(WeatherRequestType.Coordinates(loc))
    }

    private fun loadWeather(type: WeatherRequestType) {
        weatherLoadingSubs.clear()

        state.postValue(StateLoading())

        provideWeather(type)
            .map { CurrentWeatherWrapper.from(it) }
            .subscribe({
                weather.postValue(it)
                state.postValue(StateSuccess())
            }, {
                state.postValue(StateError(it))
            })
            .addTo(subs)
    }

    private fun provideWeather(type: WeatherRequestType) = when (type) {
        is WeatherRequestType.CityName -> weatherInteractor.getCurrentWeather(type.city)
        is WeatherRequestType.Coordinates -> weatherInteractor.getCurrentWeather(type.loc)
    }

    override fun onCleared() {
        weatherLoadingSubs.dispose()
        super.onCleared()
    }

    private sealed class WeatherRequestType {
        class CityName(val city: String) : WeatherRequestType()
        class Coordinates(val loc: Location) : WeatherRequestType()
    }
}