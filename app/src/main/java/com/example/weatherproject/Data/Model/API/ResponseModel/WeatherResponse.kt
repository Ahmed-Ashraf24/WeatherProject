package com.example.weatherproject.Data.Model.API.ResponseModel

data class WeatherResponse(
    val current_weather: CurrentWeather,
    val current_weather_units: CurrentWeatherUnits,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)