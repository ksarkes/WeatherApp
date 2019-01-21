package com.github.ksarkes.weatherapp.data.net

import com.github.ksarkes.weatherapp.data.entity.Weather
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("weather?units=metric")
    fun getForecast(@Query("q") city: String): Single<Response<Weather>>

    @GET("weather?units=metric")
    fun getForecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Single<Response<Weather>>

}
