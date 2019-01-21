package com.github.ksarkes.weatherapp.ui.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val state = SingleLiveEvent<State>()

    protected val subs = CompositeDisposable()

    override fun onCleared() {
        subs.clear()
        super.onCleared()
    }
}
