package com.valter.openweather.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.safeNavigate(
        @IdRes resId: Int,
        @Nullable args: Bundle? = null,
        @Nullable navOptions: NavOptions? = null,
        @Nullable navigatorExtras: Navigator.Extras? = null
) {
    try {
        navigate(resId, args, navOptions, navigatorExtras)
    } catch (e: Exception) {
        throw e
    }
}