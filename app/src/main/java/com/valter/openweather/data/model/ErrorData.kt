package com.valter.openweather.data.model

import androidx.annotation.StringRes

data class ErrorData(@StringRes val titleRes: Int,
                     @StringRes val actionTextRes: Int
)