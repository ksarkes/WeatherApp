package com.github.ksarkes.weatherapp.ui.main

import android.os.Bundle
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.databinding.ActivityMainBinding
import com.github.ksarkes.weatherapp.ui.common.BaseActivity
import com.github.ksarkes.weatherapp.ui.common.StateError
import com.github.ksarkes.weatherapp.util.extension.snack
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout = R.layout.activity_main

    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = vm

        vm.state.observeNotNull {
            when (it) {
                is StateError -> snack(binding.root, it.message)
            }
        }
    }
}
