package com.sudo.weather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudo.weather.data.data_sources.api.WeatherApi
import com.sudo.weather.data.mapper.toDomainModelAsync
import com.sudo.weather.data.utils.handlerResponseAsync
import com.sudo.weather.domain.async.withContextHandler
import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.entities.City
import com.sudo.weather.domain.entities.DailyWeather
import com.sudo.weather.domain.entities.Weather
import com.sudo.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository{

    override suspend fun getWeatherDaily(day: Int, locationKey: String): Status<List<DailyWeather>> {
        return try {
            val response = withContextHandler {
                weatherApi.getWeatherDaily(locationKey = locationKey, day = day)
            }

            val dailyWeathers = handlerResponseAsync(response)
            Status.Success(dailyWeathers.toDomainModelAsync())

        }catch (e: Exception){
            Status.Error(error = e)
        }
    }

    override suspend fun getWeatherDailyOneDay(locationKey: String): Status<DailyWeather> {
        return try {
            val response = withContextHandler {
                weatherApi.getWeatherDaily(locationKey = locationKey, day = 1)
            }

            val dailyWeathers = handlerResponseAsync(response)

            Status.Success(dailyWeathers.toDomainModelAsync()[0])
        }catch (e: Exception){
            Status.Error(error = e)
        }
    }

    override suspend fun getWeatherDetail(locationKey: String): Status<Weather> {
        return try {
            val response = withContextHandler {
                weatherApi.getWeatherDetailCurrentDay(locationKey = locationKey)
            }
            val weather = handlerResponseAsync(response)
            Status.Success(weather.toDomainModelAsync()[0])
        }catch (e: Exception){
            Status.Error(error = e)
        }
    }

    override suspend fun getWeatherOfCity(locationKey: String): Status<City> {
        TODO("Not yet implemented")
    }
}