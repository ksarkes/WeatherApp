package com.github.ksarkes.weatherapp.ui.main

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.databinding.ActivityMainBinding
import com.github.ksarkes.weatherapp.ui.common.BaseActivity
import com.github.ksarkes.weatherapp.ui.main.history.WeatherHistoryAdapter
import com.github.ksarkes.weatherapp.util.extension.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layout = R.layout.activity_main

    private val vm: MainViewModel by viewModel()

    private val adapter by lazy { WeatherHistoryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()

        vm.history.observeNotNull(adapter::updateData)
        vm.isCelsius.observeNotNull(adapter::updateUnits)
        vm.error.observeNotNull { binding.root.snack(it.message) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOC_PERMISSION_REQ_CODE && grantResults.firstOrNull() == PERMISSION_GRANTED) {
            vm.loadHomeWeather()
        }
    }

    private fun setupViews() {
        binding.vm = vm

        binding.history.adapter = adapter
        binding.history.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        binding.home.setOnClickListener {
            if (hasPermission(ACCESS_FINE_LOCATION)) {
                vm.loadHomeWeather()
            } else {
                requestPermissions(LOC_PERMISSION_REQ_CODE, ACCESS_FINE_LOCATION)
            }
        }
    }

    companion object {

        private const val LOC_PERMISSION_REQ_CODE = 12
    }
}
