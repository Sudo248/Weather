package com.sudo.weather.data.models.responses.daily_weather

import com.sudo.weather.data.models.responses.Value

data class RealFeelTemperature(
    val Maximum: Value,
    val Minimum: Value
)