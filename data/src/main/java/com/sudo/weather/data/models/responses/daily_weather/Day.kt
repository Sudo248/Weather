package com.sudo.weather.data.models.responses.daily_weather

import com.sudo.weather.data.models.responses.Value

data class Day(
    val CloudCover: Int,
    val Evapotranspiration: Value,
    val HasPrecipitation: Boolean,
    val HoursOfIce: Double,
    val HoursOfPrecipitation: Double,
    val HoursOfRain: Double,
    val HoursOfSnow: Double,
    val Ice: Value,
    val IceProbability: Int,
    val Icon: Int,
    val IconPhrase: String,
    val LongPhrase: String,
    val PrecipitationIntensity: String,
    val PrecipitationProbability: Int,
    val PrecipitationType: String,
    val Rain: Value,
    val RainProbability: Int,
    val ShortPhrase: String,
    val Snow: Value,
    val SnowProbability: Int,
    val SolarIrradiance: Value,
    val ThunderstormProbability: Int,
    val TotalLiquid: Value,
    val Wind: Wind,
    val WindGust: WindGust
)