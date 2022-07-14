package com.sudo.weather.data.models.responses.weather

import com.sudo.weather.data.models.responses.Value

data class RealFeelTemperature(
    val Imperial: Value,
    val Metric: Value
)