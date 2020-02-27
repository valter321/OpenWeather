package com.valter.openweather.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersContainer {
    val Default: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Unconfined: CoroutineDispatcher
    val IO: CoroutineDispatcher
}
