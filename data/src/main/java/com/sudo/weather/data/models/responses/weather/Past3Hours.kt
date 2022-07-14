package com.sudo.weather.data.models.responses.weather

import com.sudo.weather.data.models.responses.Value

data class Past3Hours(
    val Imperial: Value,
    val Metric: Value
)