package com.github.ksarkes.weatherapp.domain

import com.github.ksarkes.weatherapp.data.repository.SettingsRepository

class SettingsInteractor(private val settingsRepository: SettingsRepository) {

    var isCelsius: Boolean
        get() = settingsRepository.getIsCelsius()
        set(value) {
            settingsRepository.saveIsCelsius(value)
        }
}
