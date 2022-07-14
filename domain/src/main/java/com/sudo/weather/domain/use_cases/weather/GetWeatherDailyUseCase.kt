package com.sudo.weather.domain.use_cases.weather

import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.entities.DailyWeather
import com.sudo.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeatherDailyUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(day: Int, locationKey: String): Flow<Status<List<DailyWeather>>> = flow {
        emit(Status.Loading)
        val weatherDaily = repository.getWeatherDaily(day = day, locationKey = locationKey)
        emit(weatherDaily)
    }.flowOn(Dispatchers.IO)

    suspend fun oneDay(locationKey: String): Flow<Status<DailyWeather>> = flow {
        emit(Status.Loading)
        val weatherDaily = repository.getWeatherDailyOneDay(locationKey = locationKey)
        emit(weatherDaily)
    }.flowOn(Dispatchers.IO)

}