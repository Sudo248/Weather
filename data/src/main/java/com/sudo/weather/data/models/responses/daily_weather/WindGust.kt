package com.sudo.weather.data.models.responses.daily_weather

import com.sudo.weather.data.models.responses.Value

data class WindGust(
    val Direction: Direction,
    val Speed: Value
)