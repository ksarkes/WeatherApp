package com.github.ksarkes.weatherapp.ui.common

import android.arch.lifecycle.ViewModel
import com.github.ksarkes.weatherapp.util.extension.liveDataOf
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val loading = liveDataOf<Boolean>()

    val error = SingleLiveEvent<ErrorWrapper>()

    protected val subs = CompositeDisposable()

    protected fun handleError(t: Throwable) {
        loading.postValue(false)
        error.postValue(ErrorWrapper.from(t))
    }

    override fun onCleared() {
        subs.clear()
        super.onCleared()
    }
}
