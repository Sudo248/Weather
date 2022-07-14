package com.sudo.weather.data.models.responses.daily_weather

data class AirAndPollen(
    val Category: String,
    val CategoryValue: Int,
    val Name: String,
    val Type: String,
    val Value: Int
)