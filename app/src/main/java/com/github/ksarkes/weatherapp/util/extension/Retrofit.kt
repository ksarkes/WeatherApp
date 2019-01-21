package com.github.ksarkes.weatherapp.util.extension

import com.github.ksarkes.weatherapp.data.exception.ApiException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Response

fun <T> Single<Response<T>>.asRetrofitResponse() = this
    .map {
        if (!it.isSuccessful) throw ApiException(it.code(), it.errorBody()) else it
    }
    .subscribeOn(io())
    .observeOn(mainThread())

fun <T> Single<Response<T>>.asRetrofitBody() = this
    .asRetrofitResponse()
    .map { it.body()!! }!!
