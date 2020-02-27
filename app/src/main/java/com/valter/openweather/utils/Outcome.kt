package com.valter.openweather.utils

/**
* Class to wrap the data, with it is status, following the Loading/Content/Error (LCE) pattern.
*/
sealed class Outcome<T> {
    data class Progress<T>(var loading: Boolean) : Outcome<T>()
    data class Success<T>(var data: T) : Outcome<T>()
    data class Failure<T>(val e: Throwable) : Outcome<T>()

    companion object {
        fun <T> loading(isLoading: Boolean = true): Outcome<T> = Progress(isLoading)

        fun <T> success(data: T): Outcome<T> = Success(data)

        fun <T> failure(e: Throwable): Outcome<T> = Failure(e)
    }
}

/**
 * Try to return the wrapped value inside an Outcome
 * In case the Outcome doesn't have a value inside it, this property will throw an exception depending on the case (see them below)
 * Outcome.Progress: IllegalStateException
 * Outcome.Failure: The wrapped exception inside Outcome.Failure
 * The other edge case is when the Outcome is still null, in this case it will throw NullPointerException
 */
val <T> Outcome<T>?.insideValue: T
    get() {
        val nonNullValue = this ?: throw NullPointerException("This LiveData doesn't have a value inside it")

        return when (nonNullValue) {
            is Outcome.Progress -> throw IllegalStateException("This LiveData is still loading something")
            is Outcome.Failure -> throw nonNullValue.e
            is Outcome.Success -> nonNullValue.data
        }
    }
