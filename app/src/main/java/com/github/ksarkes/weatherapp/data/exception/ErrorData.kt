package com.github.ksarkes.weatherapp.data.exception

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @SerializedName("cod")
    val code: String?,
    val message: String?
)
