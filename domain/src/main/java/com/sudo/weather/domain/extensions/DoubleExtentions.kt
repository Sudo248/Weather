package com.sudo.weather.domain.extensions

fun Double.celsiusToFahrenheit(): Double = this * 1.8 + 32.0

fun Double.fahrenheitToCelsius(): Double = (this - 32.0) / 1.8

fun Double.cToF(): Double = celsiusToFahrenheit()

fun Double.fToC(): Double = fahrenheitToCelsius()