package com.sudo.weather.domain.use_cases.weather

import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.entities.City
import com.sudo.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeatherOfCityUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(locationKey: String): Flow<Status<City>> = flow {
        emit(Status.Loading)
        val weatherCity = repository.getWeatherOfCity(locationKey = locationKey)
        emit(weatherCity)
    }.flowOn(Dispatchers.IO)
}