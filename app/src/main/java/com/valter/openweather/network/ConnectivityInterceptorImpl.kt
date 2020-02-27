package com.valter.openweather.network

import android.content.Context
import com.valter.openweather.utils.NoConnectivityException
import com.valter.openweather.utils.isConnectedToNetwork
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
        context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!appContext.isConnectedToNetwork())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }
}