package com.valter.openweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valter.openweather.data.converters.ForecastListTypeConverter
import com.valter.openweather.data.converters.WeatherListTypeConverter
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData

@Database(
        entities = [CurrentWeatherData::class],
        version = 1
)

@TypeConverters(
        WeatherListTypeConverter::class,
        ForecastListTypeConverter::class
)

abstract class OpenWeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun forecastWeatherDao(): ForecastWeatherDao
}