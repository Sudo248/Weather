package com.sudo.weather.domain.use_cases.location

import com.sudo.weather.domain.common.Status
import com.sudo.weather.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLocationKeyUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(location: String): Flow<Status<String>> = flow {
        emit(Status.Loading)
        val locationKey = repository.getLocationKey(location = location)
        emit(locationKey)
    }.flowOn(Dispatchers.IO)
}