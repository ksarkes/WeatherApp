package com.github.ksarkes.weatherapp.ui.chart

import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter

data class ChartDataWrapper(
    val lineEntries: List<Entry>,
    val candleEntries: List<CandleEntry>,
    val valueFormatter: IValueFormatter,
    val axisFormatter: IAxisValueFormatter,
    val units: String
)