package com.github.ksarkes.weatherapp.ui.main

import android.os.Bundle
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.databinding.ActivityMainBinding
import com.github.ksarkes.weatherapp.ui.common.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
