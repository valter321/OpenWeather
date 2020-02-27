package com.valter.openweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valter.openweather.data.database.entity.weather.CURRENT_WEATHER_ID
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currentWeather: CurrentWeatherData)

    @Query("SELECT * FROM current_weather WHERE currentWeatherId = $CURRENT_WEATHER_ID")
    suspend fun getCurrentWeather() : CurrentWeatherData
}
