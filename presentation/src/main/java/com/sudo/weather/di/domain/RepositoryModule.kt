package com.sudo.weather.di.domain

import com.sudo.weather.data.repository.LocationRepositoryImpl
import com.sudo.weather.data.repository.WeatherRepositoryImpl
import com.sudo.weather.domain.repository.LocationRepository
import com.sudo.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Domain layer is a kotlin lib, so it can't dependency to Android framework (Hilt Dagger)
 *
 * [RepositoryModule] bind repository interface in domain layer with repository implement in data layer
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * Bind Repository
     *
     * Bind repository interface with repository implement
     */
    @Binds
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}