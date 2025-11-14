package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_weather")
data class FavWeatherEntity(
    @PrimaryKey val date: String,
    val condition: String,
    val weatherCode: Int,
    val temp: String
)