package com.github.ksarkes.weatherapp.util.binding

import android.databinding.BindingAdapter
import android.view.View

object Binding {

    @BindingAdapter("visible")
    @JvmStatic
    fun setViewVisible(view: View, obj: Any?) {
        setViewVisible(view, obj != null)
    }

    @BindingAdapter("visible")
    @JvmStatic
    fun setViewVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
