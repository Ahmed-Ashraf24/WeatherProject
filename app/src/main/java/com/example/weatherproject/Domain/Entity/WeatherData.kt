package com.example.weatherproject.Domain.Entity

sealed class WeatherData {
    data class DailyWeatherData(val daily: DailyWeather): WeatherData()
    data class ForecastWeatherData(val forecast: List<ForecastWeather>): WeatherData()
    data class CombinedWeatherData(val daily: DailyWeather,val forecast: List<ForecastWeather>): WeatherData()

}