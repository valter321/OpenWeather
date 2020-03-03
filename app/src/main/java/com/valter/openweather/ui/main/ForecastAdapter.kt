package com.valter.openweather.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.valter.openweather.R
import com.valter.openweather.data.database.entity.forecast.Forecast

class ForecastAdapter() : ListAdapter<Forecast, ForecastViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_list, parent, false)
    )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Forecast>() {
            override fun areItemsTheSame(
                    oldItem: Forecast,
                    newItem: Forecast
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                    oldItem: Forecast,
                    newItem: Forecast
            ) = oldItem == newItem
        }
    }
}