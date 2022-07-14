package com.sudo.weather.domain.entities

import java.time.LocalDateTime
import java.time.LocalTime


data class DailyWeather(
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val text: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val sunRiseTime: LocalTime,
    val sunSetTime: LocalTime,
    val hoursOfSun: Double,
    val airQuality: Int,
    val dayRainProbability: Int,
    val nightRainProbability: Int,
    val link: String?
){
    var avgTemperature: Double? = null
        get() = field ?: ((maxTemperature + minTemperature) / 2).also { avgTemperature = it }
    val rainProbability: Int
        get() = if(dateTime.hour > 16) nightRainProbability else dayRainProbability
}
