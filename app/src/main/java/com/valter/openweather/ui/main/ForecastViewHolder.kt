package com.valter.openweather.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.valter.openweather.R
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.utils.buildIconUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast_list.*
import kotlinx.android.synthetic.main.main_fragment.imgIcon
import kotlinx.android.synthetic.main.main_fragment.txtTemperature

class ForecastViewHolder(
        override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(forecast: Forecast) {
        with(forecast) {
            weather?.let {
                Glide.with(containerView.context)
                        .load(weather[0].icon.buildIconUrl())
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imgIcon)
            }


            txtTemperature.text = containerView.context.getString(R.string.temperature, main?.temp?.toInt())
            txtHour.text = date?.substring(11, 13) ?: ""
        }
    }
}