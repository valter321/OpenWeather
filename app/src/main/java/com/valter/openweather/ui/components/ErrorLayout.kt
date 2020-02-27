package com.valter.openweather.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.valter.openweather.R
import com.valter.openweather.data.model.ErrorData
import kotlinx.android.synthetic.main.widget_error.view.*

class ErrorLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(this.context).inflate(R.layout.widget_error, this, true)
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL
    }

    fun setError(error: ErrorData, listener: (() -> Unit)?) {
        // Sets the ErrorState data to the view.
        with(error) {
            txtErrorTitle.setText(titleRes)

            btnErrorAction.apply {
                setText(actionTextRes)
                setOnClickListener { listener?.invoke() }
            }
        }
    }
}