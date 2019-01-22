package com.github.ksarkes.weatherapp.ui.main.history

import android.support.v7.util.DiffUtil

class WeatherHistoryDiffUtilCallback(
    private val old: List<WeatherHistoryItemWrapper>,
    private val new: List<WeatherHistoryItemWrapper>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areContentsTheSame(oldPos: Int, newPos: Int) = old[oldPos] == new[newPos]

    override fun areItemsTheSame(oldPos: Int, newPos: Int) = old[oldPos].cityId == new[newPos].cityId
}