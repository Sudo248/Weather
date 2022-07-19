package com.sudo.weather.di.domain

import com.sudo.weather.domain.repository.LocationRepository
import com.sudo.weather.domain.repository.WeatherRepository
import com.sudo.weather.domain.use_cases.location.GetCurrentLocationKeyUseCase
import com.sudo.weather.domain.use_cases.weather.GetWeatherDailyUseCase
import com.sudo.weather.domain.use_cases.weather.GetWeatherDetailUseCase
import com.sudo.weather.domain.use_cases.weather.GetWeatherOfCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Domain layer is a kotlin lib, so it can't dependency to Android framework (Hilt Dagger)
 *
 * [UseCaseModule] provide use case in domain layer for view model in presentation layer
 */

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @ActivityRetainedScoped
    @Provides
    fun provideGetLocationKeyUseCase(
        locationRepository: LocationRepository
    ) = GetCurrentLocationKeyUseCase(repository = locationRepository)

    @ActivityRetainedScoped
    @Provides
    fun provideGetCurrentLocationKeyUseCase(
        locationRepository: LocationRepository
    ) = GetCurrentLocationKeyUseCase(repository = locationRepository)

    @ActivityRetainedScoped
    @Provides
    fun provideGetWeatherDailyUseCase(
        weatherRepository: WeatherRepository
    ) = GetWeatherDailyUseCase(repository = weatherRepository)

    @ActivityRetainedScoped
    @Provides
    fun provideGetWeatherDetailUseCase(
        weatherRepository: WeatherRepository
    ) = GetWeatherDetailUseCase(repository = weatherRepository)

    @ActivityRetainedScoped
    @Provides
    fun provideGetWeatherOfCityUseCase(
        weatherRepository: WeatherRepository
    ) = GetWeatherOfCityUseCase(repository = weatherRepository)

}