package com.valter.openweather.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.valter.openweather.R
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.ui.main.ForecastAdapter
import com.valter.openweather.utils.inflate
import kotlinx.android.synthetic.main.widget_forecast.view.*

class ForecastView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var baseAdapter: ForecastAdapter? = ForecastAdapter()

    init {
        inflate(R.layout.widget_forecast, true)
    }

    fun setData(date: String, data: List<Forecast>) {
        txtDate.text = date
        baseAdapter?.submitList(data)
        rclItems.apply {
            adapter = baseAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }
}