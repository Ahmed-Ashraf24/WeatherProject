package com.example.weatherproject.Data.Model.API.ResponseModel

data class Hourly(
    val apparent_temperature: List<Double>,
    val cloudcover: List<Int>,
    val precipitation: List<Double>,
    val pressure_msl: List<Double>,
    val relative_humidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val uv_index: List<Double>,
    val visibility: List<Double>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)