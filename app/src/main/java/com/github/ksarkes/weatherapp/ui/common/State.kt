package com.github.ksarkes.weatherapp.ui.common

open class State

open class StateLoading : State()

open class StateSuccess : State()

open class StateError(val message: String) : State() {

    constructor(t: Throwable) : this(ErrorWrapper.from(t).message)
}