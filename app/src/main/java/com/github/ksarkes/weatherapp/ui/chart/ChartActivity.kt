package com.github.ksarkes.weatherapp.ui.chart

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.data.entity.HistoryWeather
import com.github.ksarkes.weatherapp.ui.common.BaseActivity
import com.github.ksarkes.weatherapp.databinding.ActivityChartBinding
import com.github.ksarkes.weatherapp.util.extension.humanize
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import org.koin.android.viewmodel.ext.android.viewModel

class ChartActivity : BaseActivity<ActivityChartBinding>() {

    override val layout = R.layout.activity_chart

    private val vm: ChartViewModel by viewModel()

    private val cityId by lazy { intent.getIntExtra(CITY_ID, 0) }
    private val title by lazy { intent.getStringExtra(TITLE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()

        vm.history.observeNotNull { setData(it) }
        vm.loadHistoryData(cityId)
    }

    private fun setData(weather: List<HistoryWeather>) {
        val delta = weather.map { it.date }.minBy { it }!!
        val entries = weather.map {
            Entry((it.date - delta).toFloat(), it.tempCelsius.toFloat())
        }

        with(binding.lineChart) {
            data = LineData(LineDataSet(entries, "temp"))
            legend.isEnabled = false
            description.isEnabled = false
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 60 * 30f
            xAxis.valueFormatter = IAxisValueFormatter { value, axis -> (value.toLong() + delta).humanize() }
        }

        val candleEntries = weather.map {
            CandleEntry(
                (it.date - delta).toFloat(),
                it.tempMaxCelsius.toFloat(),
                it.tempMinCelsius.toFloat(),
                it.tempMinCelsius.toFloat(),
                it.tempMaxCelsius.toFloat()
            )
        }

        with(binding.candleStickChart) {
            data = CandleData(CandleDataSet(candleEntries, "candle").apply {
                decreasingColor = Color.RED
                decreasingPaintStyle = Paint.Style.FILL
                increasingColor = Color.rgb(122, 242, 84)
                increasingPaintStyle = Paint.Style.FILL
            })

            legend.isEnabled = false
            description.isEnabled = false
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 60 * 30f
            xAxis.valueFormatter = IAxisValueFormatter { value, axis -> (value.toLong() + delta).humanize() }
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