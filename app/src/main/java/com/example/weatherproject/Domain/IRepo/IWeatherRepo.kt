package com.example.weatherproject.Domain.IRepo

import com.example.weatherproject.Domain.Entity.WeatherData
import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Domain.Entity.FavWeather
import kotlinx.coroutines.flow.Flow

interface IWeatherRepo {
     fun getForecast(lat: Double,lon: Double): Flow<WeatherData>
    suspend fun addToFav(favWeather: FavWeather)
    suspend fun removeToFav(favWeather: FavWeather)

    fun getFavWeatherList(): Flow<List<FavWeather>>

}