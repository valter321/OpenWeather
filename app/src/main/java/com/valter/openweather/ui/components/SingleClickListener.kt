package com.valter.openweather.ui.components

import android.os.SystemClock
import android.view.View

const val SINGLE_LONG_CLICK_INTERVAL = 1500L
const val SINGLE_SHORT_CLICK_INTERVAL = 500L

internal class SingleClickListener(
        private val interval: Long,
        private val onSingleClick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > interval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSingleClick(v)
        }
    }
}