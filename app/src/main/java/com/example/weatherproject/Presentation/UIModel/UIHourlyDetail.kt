package com.example.weatherproject.Presentation.UIModel

data class UIHourlyDetail(
    val apparent_temperature: String,
    val cloudcover: String,
    val precipitation: String,
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val uv_index: String,
    val visibility: String,
    val weatherIconRes: Int,
    val windspeed_10m: String
)
