package com.sudo.weather.domain.repository

import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.entities.City
import com.sudo.weather.domain.entities.DailyWeather
import com.sudo.weather.domain.entities.Weather

interface WeatherRepository {
    suspend fun getWeatherDaily(day: Int, locationKey: String): Status<List<DailyWeather>>
    suspend fun getWeatherDailyOneDay(locationKey: String): Status<DailyWeather>
    suspend fun getWeatherDetail(locationKey: String): Status<Weather>
    suspend fun getWeatherOfCity(locationKey: String): Status<City>
}