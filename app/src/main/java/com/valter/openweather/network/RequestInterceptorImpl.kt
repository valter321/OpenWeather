package com.valter.openweather.network

import com.valter.openweather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptorImpl : RequestInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build()

        val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }
}