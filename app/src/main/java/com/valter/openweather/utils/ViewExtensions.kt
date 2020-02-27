package com.valter.openweather.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.valter.openweather.ui.components.SINGLE_LONG_CLICK_INTERVAL
import com.valter.openweather.R
import com.valter.openweather.ui.components.SingleClickListener

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 *  Extension-function to prevent 'common double tap' issue
 *  when you're pressing button twice very fast and double action is called.
 */
fun View.setSingleClickListener(
        interval: Long = SINGLE_LONG_CLICK_INTERVAL,
        onSingleClick: (View) -> Unit
) {
    val singleClickListener = SingleClickListener(interval) {
        onSingleClick(it)
    }
    setOnClickListener(singleClickListener)
}
