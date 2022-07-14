package com.sudo.weather.data.models.responses.daily_weather

import com.sudo.weather.data.models.responses.Value

data class Temperature(
    val Maximum: Value,
    val Minimum: Value
)