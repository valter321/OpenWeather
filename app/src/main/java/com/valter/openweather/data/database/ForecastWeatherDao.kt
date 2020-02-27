package com.valter.openweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valter.openweather.data.database.entity.forecast.Forecast

@Dao
interface ForecastWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forecast: List<Forecast>)

    @Query("SELECT * FROM current_weather")
    suspend fun getForecast() : List<Forecast>
}