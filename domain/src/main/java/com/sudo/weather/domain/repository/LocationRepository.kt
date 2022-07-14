package com.sudo.weather.domain.repository

import com.sudo.weather.domain.common.Status
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocationKey(location: String): Status<String>
    suspend fun getCurrentLocationKey(): Status<String>
}