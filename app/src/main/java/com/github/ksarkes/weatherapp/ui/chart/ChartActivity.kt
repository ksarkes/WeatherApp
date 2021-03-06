package com.github.ksarkes.weatherapp.ui.chart

import android.graphics.Paint
import android.os.Bundle
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.ui.common.BaseActivity
import com.github.ksarkes.weatherapp.databinding.ActivityChartBinding
import com.github.ksarkes.weatherapp.util.extension.color
import com.github.mikephil.charting.data.*
import org.koin.android.viewmodel.ext.android.viewModel

class ChartActivity : BaseActivity<ActivityChartBinding>() {

    override val layout = R.layout.activity_chart

    private val vm: ChartViewModel by viewModel()

    private val cityId by lazy { intent.getIntExtra(CITY_ID, 0) }
    private val title by lazy { intent.getStringExtra(TITLE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        setupViews()

        vm.chartData.observeNotNull(::setData)
        vm.loadHistoryData(cityId)
    }

    private fun setupViews() {
        setOf(binding.lineChart, binding.candleStickChart).forEach {
            with(it) {
                legend.isEnabled = false
                isHighlightPerTapEnabled = false
                isHighlightPerDragEnabled = false
                axisLeft.isEnabled = false
                xAxis.labelCount = 2
            }
        }
    }

    private fun setData(chartData: ChartDataWrapper) {
        setOf(binding.lineChart, binding.candleStickChart).forEach {
            it.description.text = chartData.units
        }

        with(binding.lineChart) {
            data = LineData(LineDataSet(chartData.lineEntries, null))
            data.setValueFormatter(chartData.valueFormatter)
            xAxis.valueFormatter = chartData.axisFormatter
        }

        with(binding.candleStickChart) {
            data = CandleData(
                CandleDataSet(chartData.candleEntries, null).apply {
                    increasingPaintStyle = Paint.Style.FILL
                    decreasingPaintStyle = Paint.Style.FILL
                    increasingColor = color(R.color.primary)
                    decreasingColor = color(R.color.primary)
                }
            )
            data.setValueFormatter(chartData.valueFormatter)
            xAxis.valueFormatter = chartData.axisFormatter
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    companion object {

        const val CITY_ID = "cityId"
        const val TITLE = "title"
    }
}