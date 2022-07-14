package com.sudo.weather.domain.entities

data class City(
    val key: Int,
    val name: String,
    val weather: Weather?,
)
