package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class DailyWithHourly(
    @Embedded val daily: DailyEntity,
    @Relation(parentColumn = "date", entityColumn = "dailyDate")
    val hourlyList: List<HourlyEntity>
)
