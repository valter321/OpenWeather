package com.valter.openweather.data.converters

import com.valter.openweather.data.database.entity.Weather

class WeatherListTypeConverter :
        ListTypeConverter<Weather>(Weather::class.java)