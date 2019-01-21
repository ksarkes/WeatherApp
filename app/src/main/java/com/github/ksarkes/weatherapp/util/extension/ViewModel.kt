package com.github.ksarkes.weatherapp.util.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (value: T?) -> Unit) {
    this.observe(lifecycleOwner, Observer(observer::invoke))
}

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (value: T) -> Unit) {
    this.observe(lifecycleOwner, Observer { it?.let(observer::invoke) })
}

fun <T> liveDataOf(defValue: T? = null) = MutableLiveData<T>().apply {
    defValue?.apply { postValue(this) }
}
