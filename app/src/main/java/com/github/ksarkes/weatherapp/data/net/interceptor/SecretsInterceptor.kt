package com.github.ksarkes.weatherapp.data.net.interceptor

import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SecretsInterceptor : Interceptor, KoinComponent {

    private val res: ResourcesHelper  by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter("APPID", res.getString(R.string.weather_api_key))
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
