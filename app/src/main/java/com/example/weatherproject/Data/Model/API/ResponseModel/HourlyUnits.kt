package com.example.weatherproject.Data.Model.API.ResponseModel

data class HourlyUnits(
    val apparent_temperature: String,
    val cloudcover: String,
    val precipitation: String,
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val uv_index: String,
    val visibility: String,
    val weathercode: String,
    val windspeed_10m: String
)