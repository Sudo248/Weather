package com.sudo.weather.data.models.responses.weather

data class PrecipitationSummary(
    val Past12Hours: Past12Hours,
    val Past18Hours: Past18Hours,
    val Past24Hours: Past24Hours,
    val Past3Hours: Past3Hours,
    val Past6Hours: Past6Hours,
    val Past9Hours: Past9Hours,
    val PastHour: PastHour,
    val Precipitation: Precipitation
)