package com.github.ksarkes.weatherapp.ui.common

import com.github.ksarkes.weatherapp.BuildConfig
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.data.exception.ApiException
import com.github.ksarkes.weatherapp.util.helper.ResourcesHelper
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.IOException

data class ErrorWrapper(val message: String) {

    companion object : KoinComponent {

        fun from(thr: Throwable): ErrorWrapper {

            val res: ResourcesHelper by inject()

            if (BuildConfig.DEBUG) thr.printStackTrace()

            val msg = when (thr) {
                is ApiException -> thr.error?.message
                is IOException -> res.getString(R.string.error_connection)
                else -> null
            } ?: res.getString(R.string.error_undefined)

            return ErrorWrapper(msg)
        }
    }
}
