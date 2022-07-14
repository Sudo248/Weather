package com.sudo.weather.data.models.responses.daily_weather

data class Sun(
    val EpochRise: Int,
    val EpochSet: Int,
    val Rise: String,
    val Set: String
)