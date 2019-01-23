package com.github.ksarkes.weatherapp.data.repository

import android.content.SharedPreferences

class SettingsRepository(private val sp: SharedPreferences) {

    fun getIsCelsius() = sp.getBoolean(IS_CELSIUS, true)

    fun saveIsCelsius(isCelsius: Boolean) {
        sp.edit().putBoolean(IS_CELSIUS, isCelsius).apply()
    }

    companion object {

        private const val IS_CELSIUS = "is_celsius"
    }
}
