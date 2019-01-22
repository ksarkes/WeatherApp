package com.github.ksarkes.weatherapp.util.helper

import android.content.Context
import android.support.v4.content.ContextCompat

class ResourcesHelper(private val context: Context) {

    private val res = context.resources

    fun getString(id: Int): String = res.getString(id)

    fun getString(id: Int, vararg formats: Any): String = res.getString(id, *formats)

    fun getColor(id: Int) = ContextCompat.getColor(context, id)
}
