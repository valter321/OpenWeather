package com.valter.openweather.data.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class ListTypeConverter<T>(private val tClass: Class<T>) : KoinComponent {

    private val moshi by inject<Moshi>()

    @TypeConverter
    fun fromObject(list: List<T>?): String? {
        if (list.isNullOrEmpty()) {
            return null
        }

        val parameterizedType =
                Types.newParameterizedType(List::class.java, tClass)
        val adapter: JsonAdapter<List<T>> = moshi.adapter(parameterizedType)

        return adapter.toJson(list)
    }

    @TypeConverter
    fun toObject(source: String?): List<T>? {
        if (source.isNullOrBlank()) {
            return null
        }

        val parameterizedType =
                Types.newParameterizedType(List::class.java, tClass)
        val adapter: JsonAdapter<List<T>> = moshi.adapter(parameterizedType)

        return adapter.fromJson(source)
    }
}
