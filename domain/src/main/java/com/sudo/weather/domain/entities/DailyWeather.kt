package com.sudo.weather.domain.entities

import java.util.Calendar

data class DailyWeather(
    val dateTime: Calendar = Calendar.getInstance(),
    val text: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val sunRiseTime: Calendar,
    val sunSetTime: Calendar,
    val hoursOfSun: Double,
    val airQuality: Int,
    val dayRainProbability: Int,
    val nightRainProbability: Int,
    val link: String?
){
    var avgTemperature: Double? = null
        get() = field ?: ((maxTemperature + minTemperature) / 2).also { avgTemperature = it }
    val rainProbability: Int
        get() = if(dateTime.get(Calendar.HOUR_OF_DAY) > 16) nightRainProbability else dayRainProbability
}
