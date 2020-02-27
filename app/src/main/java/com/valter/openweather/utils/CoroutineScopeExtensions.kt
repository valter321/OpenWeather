package com.valter.openweather.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * This is a shortcut that wraps the safety call inside a [CoroutineScope]
 * This method automatically place the [block] parameter inside a safe try-catch wrapper and converts the result to the specific [Outcome] type
 * If the [block] manages to be executed successfully, the [mutableLiveData] will post an [Outcome.Success] value
 * If the [block] causes an widget_error, the [mutableLiveData] will post an [Outcome.Failure] value
 * Before the [block] is executed and if [loading] is true, the [mutableLiveData] will emit an [Outcome.Progress] value
 * It's important to mention that the [block] function must return the type that [Outcome] wraps and not [Outcome]
 */
fun <T> CoroutineScope.launchSafely(
        mutableLiveData: MutableLiveData<Outcome<T>>,
        context: CoroutineContext = EmptyCoroutineContext,
        loading: Boolean = true,
        block: suspend () -> T
) = launch(context) {
    withSafety(mutableLiveData, loading, block)
}

/**
 * This method automatically place the suspend [function] parameter inside a safe try-catch wrapper and converts the result to the specific [Outcome] type
 * If the [function] manages to be executed successfully, the [mutableLiveData] will post an [Outcome.Success] value
 * If the [function] causes an widget_error, the [mutableLiveData] will post an [Outcome.Failure] value
 * Before the [function] is executed and if [loading] is true, the [mutableLiveData] will emit an [Outcome.Progress] value
 * It's important to mention that the [function] function must return the type that [Outcome] wraps and not [Outcome]
 */
suspend fun <T> withSafety(mutableLiveData: MutableLiveData<Outcome<T>>, loading: Boolean = true, function: suspend () -> T) {
    try {
        if (loading) {
            mutableLiveData.postValue(Outcome.loading(true))
        }

        val result = function()

        mutableLiveData.postValue(Outcome.success(result))
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        mutableLiveData.postValue(Outcome.failure(throwable))
    }
}