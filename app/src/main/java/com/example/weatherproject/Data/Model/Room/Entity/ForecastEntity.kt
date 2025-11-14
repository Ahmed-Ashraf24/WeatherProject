package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherproject.Domain.Entity.HourlyData
@Entity(tableName = "Forecast_Table")
data class ForecastEntity(
    @PrimaryKey
    val date: String,
    val city: String,
    val temp: String,
    val weatherCode: Int,
    val windSpeed: String,
    val precipitation:String,
    val uv: String,
    val uvClearSky:String,
    val apparentTemp:String,
    val windGust:String,
    val shortwaveRadiation:String,
    val evapotranspiration:String
)
