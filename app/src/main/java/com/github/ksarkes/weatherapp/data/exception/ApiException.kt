package com.github.ksarkes.weatherapp.data.exception

import com.google.gson.Gson
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

data class ApiException(val code: Int, val response: ResponseBody?) : Exception(), KoinComponent {

    val error: ErrorData?

    private val gson: Gson by inject()

    init {
        error = try {
            gson.fromJson(response?.string(), ErrorData::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
