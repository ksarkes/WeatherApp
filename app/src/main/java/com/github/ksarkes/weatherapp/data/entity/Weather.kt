package com.github.ksarkes.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class Weather(
    val id: Int,
    val name: String,
    val main: MainInfo,
    @SerializedName("dt")
    val date: Long
)
