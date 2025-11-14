package com.example.weatherproject.Data.Mapper.Mapper

import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity
import com.example.weatherproject.Domain.Entity.FavWeather

object FavWeatherMapper {
    fun toFavWeather(favWeatherEntity: FavWeatherEntity): FavWeather {
        return FavWeather(
            favWeatherEntity.date,
            favWeatherEntity.condition,
            favWeatherEntity.weatherCode,
            favWeatherEntity.temp
        )
    }
}