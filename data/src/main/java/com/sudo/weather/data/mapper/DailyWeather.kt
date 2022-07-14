package com.sudo.weather.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudo.weather.data.models.responses.daily_weather.DailyForecast
import com.sudo.weather.data.models.responses.daily_weather.DailyWeatherResponse
import com.sudo.weather.domain.async.asyncTask
import com.sudo.weather.domain.entities.DailyWeather
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.S)
fun DailyWeatherResponse.toDomainModel(): List<DailyWeather> {
    val textHeadline = this.Headline.Text;
    return this.DailyForecasts.map {
        it.toDomainModel(textHeadline = textHeadline)
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun DailyForecast.toDomainModel(textHeadline: String = ""): DailyWeather{
    return DailyWeather(
        text = textHeadline,
        maxTemperature = this.Temperature.Maximum.Value,
        minTemperature = this.Temperature.Minimum.Value,
        sunRiseTime = LocalTime.ofInstant(Instant.ofEpochSecond(this.Sun.EpochRise.toLong()), ZoneId.systemDefault()),
        sunSetTime = LocalTime.ofInstant(Instant.ofEpochSecond(this.Sun.EpochSet.toLong()), ZoneId.systemDefault()),
        hoursOfSun = this.HoursOfSun,
        airQuality = this.AirAndPollen[0].Value,
        dayRainProbability = this.Day.RainProbability,
        nightRainProbability = this.Night.RainProbability,
        link = this.Link
    )
}

@RequiresApi(Build.VERSION_CODES.S)
suspend fun DailyWeatherResponse.toDomainModelAsync(): List<DailyWeather> {
    val textHeadline = this.Headline.Text
    val res: List<Deferred<DailyWeather>> = this.DailyForecasts.map {
        asyncTask(Dispatchers.Default) { it.toDomainModelAsync(textHeadline = textHeadline) }
    }
    return res.awaitAll()
}

@RequiresApi(Build.VERSION_CODES.S)
suspend fun DailyForecast.toDomainModelAsync(textHeadline: String = ""): DailyWeather{
    return DailyWeather(
        text = textHeadline,
        maxTemperature = this.Temperature.Maximum.Value,
        minTemperature = this.Temperature.Minimum.Value,
        hoursOfSun = this.HoursOfSun,
        airQuality = this.AirAndPollen[0].Value,
        dayRainProbability = this.Day.RainProbability,
        nightRainProbability = this.Night.RainProbability,
        link = this.Link,
        sunRiseTime = withContext(Dispatchers.Default) { LocalTime.ofInstant(Instant.ofEpochSecond(this@toDomainModelAsync.Sun.EpochRise.toLong()), ZoneId.systemDefault()) },
        sunSetTime = withContext(Dispatchers.Default) { LocalTime.ofInstant(Instant.ofEpochSecond(this@toDomainModelAsync.Sun.EpochSet.toLong()), ZoneId.systemDefault()) }
    )
}