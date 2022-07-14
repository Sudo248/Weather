package com.sudo.weather.data.models.responses.daily_weather

data class DailyWeatherResponse(
    val DailyForecasts: List<DailyForecast>,
    val Headline: Headline
)