package com.github.ksarkes.weatherapp.domain

import com.github.ksarkes.weatherapp.data.repository.LocationRepository

class LocationInteractor(private val locationRepository: LocationRepository) {

    fun getLocation() = locationRepository.getLocation()
}