package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.BuildConfig.BASE_URL
import com.github.ksarkes.weatherapp.data.net.interceptor.SecretsInterceptor
import com.github.ksarkes.weatherapp.data.net.Api
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Api> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(Api::class.java)
    }

    single<OkHttpClient> {
        with(OkHttpClient.Builder()) {
            addInterceptor(SecretsInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
            build()
        }
    }

    single<Gson> {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}
