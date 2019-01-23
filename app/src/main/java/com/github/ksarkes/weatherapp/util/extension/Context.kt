package com.github.ksarkes.weatherapp.util.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.annotation.ColorRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlin.reflect.KClass

fun Context.hasPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.start(clazz: KClass<*>, block: (Intent.() -> Unit)? = null) {
    Intent(this, clazz.java).let {
        block?.invoke(it)
        this.startActivity(it)
    }
}

fun Activity.requestPermissions(reqCode: Int, vararg permission: String) {
    ActivityCompat.requestPermissions(this, permission, reqCode)
}

fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)