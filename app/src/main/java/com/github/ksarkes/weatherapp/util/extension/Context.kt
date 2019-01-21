package com.github.ksarkes.weatherapp.util.extension

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View

fun Context.snack(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, duration).show()
}
