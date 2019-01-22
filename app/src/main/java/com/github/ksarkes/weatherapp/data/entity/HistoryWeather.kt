package com.github.ksarkes.weatherapp.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HistoryWeather(
    open var cityId: Int = 0,
    open var cityName: String = "",
    open var tempCelsius: Double = .0,
    open var tempMinCelsius: Double = .0,
    open var tempMaxCelsius: Double = .0,
    open var date: Long = 0L,
    open var requestTime: Long = 0L,
    @PrimaryKey open var uid: String = "${cityId}_$date"
) : RealmObject()
