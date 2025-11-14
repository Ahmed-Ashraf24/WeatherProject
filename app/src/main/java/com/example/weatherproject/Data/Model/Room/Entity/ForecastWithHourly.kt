package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastWithHourly (
    @Embedded val forecast: ForecastEntity,

    @Relation(
        parentColumn = "date",
        entityColumn = "forecastDate"
    )
    val hourlyList: List<HourlyEntity>
)