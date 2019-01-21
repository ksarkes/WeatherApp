package com.github.ksarkes.weatherapp.util.helper

import android.content.Context

class ResourcesHelper(context: Context) {

    private val res = context.resources

    fun getString(id: Int): String = res.getString(id)

    fun getString(id: Int, vararg formats: Any): String = res.getString(id, *formats)
}
