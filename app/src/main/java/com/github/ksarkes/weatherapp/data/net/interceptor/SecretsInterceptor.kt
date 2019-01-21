package com.github.ksarkes.weatherapp.data.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class SecretsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter("APPID", "")
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
