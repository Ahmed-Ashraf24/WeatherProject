package com.example.weatherproject.Domain.IRepo

import com.example.weatherproject.Data.Model.API.WeatherData
import com.example.weatherproject.Domain.Entity.DailyWeather
import kotlinx.coroutines.flow.Flow

interface IWeatherRepo {
     fun getForecast(): Flow<WeatherData>
}