package com.sudo.weather.domain.entities

import java.time.LocalDate

data class Weather(
    val date: LocalDate,
    // Mô tả thời tiết hiện tai.
    val weatherText: String,
    // Nhiệt độ hiện tại. Unit: C
    val temperature: Double,
    // Nhiệt độ cảm thấy
    val realFeelTemperature: Double,
    // Nhiệt độ cảm thấy trong bóng râm.
    val realFeelTemperatureShade: Double,
    // Độ ẩm tương đối.
    val relativeHumidity: Int,
    // Độ ẩm tương đối trong nhà.
    val indoorRelativeHumidity: Int,
    // Điểm sương mù.
    val dewPoint: Double,
    // Hướng gió
    val directionWind: String,
    // Tốc độ gió (km/h)
    val speedWind: Double,
    // Chỉ số UV
    val uVIndex: Int,
    //
    val uVIndexText: String,
    // Tầm nhìn (km)
    val visibility: Double,
    // Vật cản.
    val obstructionsToVisibility: String,
    // Mây che phủ (%)
    val cloudCover: Int,
    // Áp suất (mb)
    val pressure: Double,
    // nhiệt độ chênh lệch.
    val past24HourTemperatureDeparture: Double,
    // Lượng mưa tổng hợp (mm)
    val precipitationSummary: Double,
    val link: String?,
    )
