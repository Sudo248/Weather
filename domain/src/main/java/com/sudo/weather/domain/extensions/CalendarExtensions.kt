package com.sudo.weather.domain.extensions

import java.util.Calendar

val Calendar.hourMinutes get() = "${get(Calendar.HOUR_OF_DAY)}:${get(Calendar.MINUTE)}"

fun calendarFromMilliSeconds(milliseconds: Long): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliseconds
    return calendar
}

