package com.sudo.weather.data.models.responses.daily_weather

import com.sudo.weather.data.models.responses.Value

data class DegreeDaySummary(
    val Cooling: Value,
    val Heating: Value
)