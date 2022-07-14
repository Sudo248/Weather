package com.sudo.weather.domain.extensions

import java.time.LocalTime

val LocalTime.hourMinutes get() = "${hour}:${minute}"
