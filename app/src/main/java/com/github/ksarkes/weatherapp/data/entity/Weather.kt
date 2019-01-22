package com.github.ksarkes.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    val cityId: Int,
    @SerializedName("name")
    val cityName: String,
    val main: MainInfo,
    @SerializedName("dt")
    val date: Long
)
