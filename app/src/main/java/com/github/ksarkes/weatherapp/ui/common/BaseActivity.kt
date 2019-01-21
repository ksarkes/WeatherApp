package com.github.ksarkes.weatherapp.ui.common

import android.arch.lifecycle.LiveData
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.ksarkes.weatherapp.util.extension.observe
import com.github.ksarkes.weatherapp.util.extension.observeNotNull

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        binding.setLifecycleOwner(this)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    protected fun <T> LiveData<T>.observe(observer: (value: T?) -> Unit) {
        this.observe(this@BaseActivity, observer)
    }

    protected fun <T> LiveData<T>.observeNotNull(observer: (value: T) -> Unit) {
        this.observeNotNull(this@BaseActivity, observer)
    }
}
