package com.sudo.weather.data.data_sources.api

import com.sudo.weather.data.ConstantData
import com.sudo.weather.data.models.responses.daily_weather.DailyWeatherResponse
import com.sudo.weather.data.models.responses.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecasts/v1/daily/{numberDays}day/{locationKey}")
    suspend fun getWeatherDaily(
        @Path("locationKey") locationKey: String,
        @Path("numberDays") day: Int,
        @Query("apikey") apiKey: String = ConstantData.apiKey,
        @Query("details") details: Boolean = true
    ): Response<DailyWeatherResponse>

    @GET("currentconditions/v1/{locationKey}")
    suspend fun getWeatherDetailCurrentDay(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String = ConstantData.apiKey,
        @Query("details") details: Boolean = true
    ): Response<WeatherResponse>

    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getWeatherNext5Day(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String = ConstantData.apiKey,
        @Query("details") details: Boolean = true
    ): Response<DailyWeatherResponse>


}