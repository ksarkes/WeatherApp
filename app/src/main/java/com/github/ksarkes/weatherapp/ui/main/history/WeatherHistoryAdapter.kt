package com.github.ksarkes.weatherapp.ui.main.history

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.databinding.ItemWeatherHistoryBinding
import com.github.ksarkes.weatherapp.util.extension.inflate

class WeatherHistoryAdapter : RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder>() {

    var data = listOf<WeatherHistoryItemWrapper>()

    fun updateData(data: List<WeatherHistoryItemWrapper>) {
        val diff = DiffUtil.calculateDiff(WeatherHistoryDiffUtilCallback(this.data, data))
        this.data = data
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.inflate<ItemWeatherHistoryBinding>(R.layout.item_weather_history)
        binding.chart.setOnClickListener { }
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(private val binding: ItemWeatherHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherHistoryItemWrapper) {
            binding.item = item
        }
    }
}
