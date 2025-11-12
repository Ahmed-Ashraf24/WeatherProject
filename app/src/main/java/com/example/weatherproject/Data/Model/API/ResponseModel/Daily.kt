package com.example.weatherproject.Data.Model.API.ResponseModel

data class Daily(
    val precipitation_sum: List<Double>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val apparent_temperature_max: List<Double>,
    val apparent_temperature_min: List<Double>,
    val wind_gusts_10m_max: List<Double>,
    val shortwave_radiation_sum: List<Double>,
    val et0_fao_evapotranspiration: List<Double>,

    val time: List<String>,
    val uv_index_clear_sky_max: List<Double>,
    val uv_index_max: List<Double>,
    val weathercode: List<Int>,
    val wind_speed_10m_max: List<Double>
)