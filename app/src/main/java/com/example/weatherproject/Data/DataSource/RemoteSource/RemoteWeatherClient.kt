package com.example.weatherproject.Data.DataSource.RemoteSource

import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse

interface RemoteWeatherClient {
   suspend fun getWeather( latitude: Double, longitude: Double): WeatherResponse
}