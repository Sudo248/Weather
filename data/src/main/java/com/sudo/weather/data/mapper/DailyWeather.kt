package com.sudo.weather.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudo.weather.data.models.responses.daily_weather.DailyForecast
import com.sudo.weather.data.models.responses.daily_weather.DailyWeatherResponse
import com.sudo.weather.domain.async.asyncTask
import com.sudo.weather.domain.entities.DailyWeather
import com.sudo.weather.domain.extensions.calendarFromMilliSeconds
import kotlinx.coroutines.*

fun DailyWeatherResponse.toDomainModel(): List<DailyWeather> {
    val textHeadline = this.Headline.Text;
    return this.DailyForecasts.map {
        it.toDomainModel(textHeadline = textHeadline)
    }
}

fun DailyForecast.toDomainModel(textHeadline: String = ""): DailyWeather{
    return DailyWeather(
        text = textHeadline,
        maxTemperature = this.Temperature.Maximum.Value,
        minTemperature = this.Temperature.Minimum.Value,
        sunRiseTime = calendarFromMilliSeconds(this.Sun.EpochRise.toLong()),
        sunSetTime = calendarFromMilliSeconds(this.Sun.EpochRise.toLong()),
        hoursOfSun = this.HoursOfSun,
        airQuality = this.AirAndPollen[0].Value,
        dayRainProbability = this.Day.RainProbability,
        nightRainProbability = this.Night.RainProbability,
        link = this.Link
    )
}

suspend fun DailyWeatherResponse.toDomainModelAsync(): List<DailyWeather> {
    val textHeadline = this.Headline.Text
    val res: List<Deferred<DailyWeather>> = this.DailyForecasts.map {
        asyncTask(Dispatchers.Default) { it.toDomainModelAsync(textHeadline = textHeadline) }
    }
    return res.awaitAll()
}

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
        sunRiseTime = withContext(Dispatchers.Default) { calendarFromMilliSeconds(this@toDomainModelAsync.Sun.EpochRise.toLong()) },
        sunSetTime = withContext(Dispatchers.Default) { calendarFromMilliSeconds(this@toDomainModelAsync.Sun.EpochSet.toLong()) }
    )
}