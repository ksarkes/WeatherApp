package com.github.ksarkes.weatherapp.ui.main.history

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.ksarkes.weatherapp.R
import com.github.ksarkes.weatherapp.databinding.ItemWeatherHistoryBinding
import com.github.ksarkes.weatherapp.ui.chart.ChartActivity
import com.github.ksarkes.weatherapp.ui.chart.ChartActivity.Companion.CITY_ID
import com.github.ksarkes.weatherapp.ui.chart.ChartActivity.Companion.TITLE
import com.github.ksarkes.weatherapp.util.extension.inflate
import com.github.ksarkes.weatherapp.util.extension.start

class WeatherHistoryAdapter(private val context: Context) : RecyclerView.Adapter<WeatherHistoryAdapter.ViewHolder>() {

    var data = listOf<WeatherHistoryItemWrapper>()

    fun updateData(data: List<WeatherHistoryItemWrapper>) {
        val diff = DiffUtil.calculateDiff(WeatherHistoryDiffUtilCallback(this.data, data))
        this.data = data
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = parent.inflate<ItemWeatherHistoryBinding>(R.layout.item_weather_history)
        val holder = ViewHolder(binding)

        binding.chart.setOnClickListener {
            val id = data[holder.adapterPosition].cityId
            val title = data[holder.adapterPosition].cityName

            context.start(ChartActivity::class) {
                putExtra(CITY_ID, id)
                putExtra(TITLE, title)
            }
        }

        return holder
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
