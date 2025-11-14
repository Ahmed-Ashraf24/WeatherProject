package com.example.weatherproject.Domain.Entity

import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity

data class FavWeather(
    val date: String,
    val condition: String,
    val weatherCode: Int,
    val temp: String
)
fun FavWeather.toFavWeatherEntity(): FavWeatherEntity{
    return FavWeatherEntity(
        this.date,
        this.condition,
        this.weatherCode,
        this.temp
    )
}
