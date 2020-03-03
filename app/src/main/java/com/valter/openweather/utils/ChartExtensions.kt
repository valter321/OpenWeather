package com.valter.openweather.utils

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.LineDataSet

fun LineDataSet.styleChartLine(lineColor: Int) = this.apply {
    color = lineColor
    valueTextColor = lineColor
    mode = LineDataSet.Mode.CUBIC_BEZIER
    lineWidth = 3f
    circleRadius = 5f
    setCircleColor(lineColor)
    setDrawCircleHole(false)
    setDrawValues(false)
}

fun LineChart.styleChart(lineColor: Int, labelText: String) = this.apply {
    axisLeft.setDrawLabels(false)
    xAxis.setDrawLabels(false)
    xAxis.isEnabled = false
    axisLeft.isEnabled = false
    description.isEnabled = false
    legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    legend.setCustom(mutableListOf(LegendEntry().apply {
        label = labelText
        formColor = lineColor
    }))
    invalidate()
}