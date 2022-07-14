package com.sudo.weather.data.models.responses.daily_weather

data class Moon(
    val Age: Int,
    val EpochRise: Int,
    val EpochSet: Int,
    val Phase: String,
    val Rise: String,
    val Set: String
)