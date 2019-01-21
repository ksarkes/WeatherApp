package com.github.ksarkes.weatherapp.core.di

import com.github.ksarkes.weatherapp.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
}
