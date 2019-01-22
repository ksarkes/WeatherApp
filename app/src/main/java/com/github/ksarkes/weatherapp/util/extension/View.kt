package com.github.ksarkes.weatherapp.util.extension

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun <T : ViewDataBinding> ViewGroup.inflate(@LayoutRes layout: Int) =
    DataBindingUtil.inflate<T>(
        LayoutInflater.from(context),
        layout,
        this,
        false
    )

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.invisible(invisible: Boolean = true) {
    this.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}
