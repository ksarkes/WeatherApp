package com.github.ksarkes.weatherapp.data.repository

import android.content.SharedPreferences
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper

class SettingsRepository(
    private val sp: SharedPreferences,
    private val res: ResourcesHelper
) {

    fun getIsCelsius() = sp.getBoolean(IS_CELSIUS, true)

    fun saveIsCelsius(isCelsius: Boolean) {
        sp.edit().putBoolean(IS_CELSIUS, isCelsius).apply()
    }

    fun getUnitsName() = res.getString(if (getIsCelsius()) R.string.celsius else R.string.fahrenheit)

    companion object {

        private const val IS_CELSIUS = "is_celsius"
    }
}
