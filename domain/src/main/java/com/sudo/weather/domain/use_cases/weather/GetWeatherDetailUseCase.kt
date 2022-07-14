package com.sudo.weather.domain.use_cases.weather

import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.entities.Weather
import com.sudo.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeatherDetailUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(locationKey: String): Flow<Status<Weather>> = flow {
        emit(Status.Loading)
        val weatherDetail = repository.getWeatherDetail(locationKey = locationKey)
        emit(weatherDetail)
    }.flowOn(Dispatchers.IO)
}