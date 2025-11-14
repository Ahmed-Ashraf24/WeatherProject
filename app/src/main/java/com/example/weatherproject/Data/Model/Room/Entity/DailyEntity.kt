package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherproject.Domain.Entity.HourlyData

@Entity(tableName = "Daily_Table")
data class DailyEntity(
    @PrimaryKey
    val id:Int=0,
    val city: String,
    val temp: String,
    val weatherCode: Int,
    val windSpeed: String,
    val uv: Double,
    val date: String,
    val isMorning: Boolean
)
