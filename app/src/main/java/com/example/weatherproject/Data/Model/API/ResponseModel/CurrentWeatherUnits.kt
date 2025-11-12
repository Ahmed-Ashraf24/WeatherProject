package com.example.weatherproject.Data.Model.API.ResponseModel

data class CurrentWeatherUnits(
    val interval: String,
    val is_day: String,
    val temperature: String,
    val time: String,
    val weathercode: String,
    val winddirection: String,
    val windspeed: String
)