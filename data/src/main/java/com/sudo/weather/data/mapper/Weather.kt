package com.sudo.weather.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudo.weather.data.models.responses.weather.WeatherResponse
import com.sudo.weather.data.models.responses.weather.WeatherResponseItem
import com.sudo.weather.domain.async.asyncHandler
import com.sudo.weather.domain.async.asyncTask
import com.sudo.weather.domain.async.withContextHandler
import com.sudo.weather.domain.entities.Weather
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
fun WeatherResponseItem.toDomainModel(): Weather{
    return Weather(
        date = LocalDate.ofEpochDay(this.EpochTime.toLong()),
        weatherText = this.WeatherText,
        temperature = this.Temperature.Metric.Value,
        realFeelTemperature = this.RealFeelTemperature.Metric.Value,
        realFeelTemperatureShade = this.RealFeelTemperatureShade.Metric.Value,
        relativeHumidity = this.RelativeHumidity,
        indoorRelativeHumidity = this.IndoorRelativeHumidity,
        dewPoint = this.DewPoint.Metric.Value,
        directionWind = this.Wind.Direction.Localized,
        speedWind = this.Wind.Speed.Metric.Value,
        uVIndex = this.UVIndex,
        uVIndexText = this.UVIndexText,
        visibility = this.Visibility.Metric.Value,
        obstructionsToVisibility = this.ObstructionsToVisibility,
        cloudCover = this.CloudCover,
        pressure = this.Pressure.Metric.Value,
        past24HourTemperatureDeparture = this.Past24HourTemperatureDeparture.Imperial.Value,
        precipitationSummary = this.PrecipitationSummary.Precipitation.Metric.Value,
        link = this.Link
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherResponse.toDomainModel(): List<Weather>{
    return this.map { it.toDomainModel() }
}

@RequiresApi(Build.VERSION_CODES.O)
suspend fun WeatherResponseItem.toDomainModelAsync(): Weather{
    return Weather(
        weatherText = this.WeatherText,
        temperature = this.Temperature.Metric.Value,
        realFeelTemperature = this.RealFeelTemperature.Metric.Value,
        realFeelTemperatureShade = this.RealFeelTemperatureShade.Metric.Value,
        relativeHumidity = this.RelativeHumidity,
        indoorRelativeHumidity = this.IndoorRelativeHumidity,
        dewPoint = this.DewPoint.Metric.Value,
        directionWind = this.Wind.Direction.Localized,
        speedWind = this.Wind.Speed.Metric.Value,
        uVIndex = this.UVIndex,
        uVIndexText = this.UVIndexText,
        visibility = this.Visibility.Metric.Value,
        obstructionsToVisibility = this.ObstructionsToVisibility,
        cloudCover = this.CloudCover,
        pressure = this.Pressure.Metric.Value,
        past24HourTemperatureDeparture = this.Past24HourTemperatureDeparture.Imperial.Value,
        precipitationSummary = this.PrecipitationSummary.Precipitation.Metric.Value,
        link = this.Link,
        date = withContext(Dispatchers.Default) { LocalDate.ofEpochDay(this@toDomainModelAsync.EpochTime.toLong()) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
suspend fun WeatherResponse.toDomainModelAsync(): List<Weather>{
    val res: List<Deferred<Weather>> = this.map { asyncTask(context = Dispatchers.Default) { it.toDomainModelAsync() } }
    return res.awaitAll()
}
