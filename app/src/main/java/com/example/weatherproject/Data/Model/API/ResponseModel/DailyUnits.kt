package com.example.weatherproject.Data.Model.API.ResponseModel

data class DailyUnits(
    val precipitation_sum: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val apparent_temperature_min: String,
    val apparent_temperature_max: String,
    val et0_fao_evapotranspiration:String,
    val shortwave_radiation_sum:String,
    val wind_gusts_10m_max: String,
    val uv_index_clear_sky_max: String,
    val uv_index_max: String,
    val weathercode: String,
    val wind_speed_10m_max: String
)