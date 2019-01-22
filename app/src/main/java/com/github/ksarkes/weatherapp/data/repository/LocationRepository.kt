package com.github.ksarkes.weatherapp.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation

class LocationRepository(private val rxLocation: RxLocation) {

    private val defaultRequest = LocationRequest
        .create()
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        .setInterval(5000)

    fun getLocation(request: LocationRequest = defaultRequest) = rxLocation
        .settings()
        .checkAndHandleResolution(request)
        .flatMap { tryGetLocation(request) }

    @SuppressLint("MissingPermission")
    private fun tryGetLocation(request: LocationRequest) = rxLocation
        .location()
        .updates(request)
        .retry(3)
        .firstOrError()
}