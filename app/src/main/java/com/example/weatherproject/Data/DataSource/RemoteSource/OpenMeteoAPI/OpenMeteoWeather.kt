package com.example.weatherproject.Data.DataSource.RemoteSource.OpenMeteoAPI

import com.example.weatherproject.Data.DataSource.RemoteSource.RemoteWeatherClient
import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Data.Model.API.RetrofitClinet.WeatherRetrofitClient

class OpenMeteoWeather() : RemoteWeatherClient {
    private val api = WeatherRetrofitClient.api
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        return api.getForecast(latitude, longitude)
    }
}