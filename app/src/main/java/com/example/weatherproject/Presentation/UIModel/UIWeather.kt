package com.example.weatherproject.Presentation.UIModel

data class UIWeather(
    val city: String,
    val temp: String,
    val weatherIconRes:Int,
    val windSpeed: String,
    val uv: Double,
    val hourly: List<UIHourlyData>,
    val condition:String,
    val date:String,
    val isMorning: Boolean,
)
