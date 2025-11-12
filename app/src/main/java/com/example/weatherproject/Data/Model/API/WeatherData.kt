package com.example.weatherproject.Data.Model.API

import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Domain.Entity.ForecastWeather

sealed interface WeatherData {
    data class DailyWeatherData(val daily: DailyWeather): WeatherData
    data class ForecastWeatherData(val forecast: List<ForecastWeather>): WeatherData
}