package com.github.ksarkes.weatherapp.ui.chart

import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter

data class ChartDataWrapper(
    val lineEntries: List<Entry>,
    val candleEntries: List<CandleEntry>,
    val formatter: IAxisValueFormatter
)