package com.example.weatherproject.Data.DataSource.LocalSource

import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity
import com.example.weatherproject.Domain.Entity.FavWeather
import com.example.weatherproject.Domain.Entity.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherCachingClint {
    suspend fun cacheWeather(weatherResponse: WeatherResponse)
    fun getCachedWeather():Flow<WeatherData>
   suspend fun addFavWeather(favWeather: FavWeatherEntity)
    suspend fun removeFavWeather(favWeather: FavWeatherEntity)

    fun getFavWeatherList(): Flow<List<FavWeatherEntity>>
}