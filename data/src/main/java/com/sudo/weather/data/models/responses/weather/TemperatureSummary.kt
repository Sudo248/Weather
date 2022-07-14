package com.sudo.weather.data.models.responses.weather

data class TemperatureSummary(
    val Past12HourRange: Past12HourRange,
    val Past24HourRange: Past24HourRange,
    val Past6HourRange: Past6HourRange
)