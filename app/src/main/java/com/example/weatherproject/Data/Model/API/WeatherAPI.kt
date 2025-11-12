package com.example.weatherproject.Data.Model.API

import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v1/forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String = "auto",
        @Query("current_weather") currentWeather: Boolean = true,
        @Query("hourly")
        hourly: String = "temperature_2m,apparent_temperature,relative_humidity_2m,dew_point_2m,pressure_msl,cloudcover,visibility,windspeed_10m,uv_index,precipitation,weathercode",
        @Query("daily")
        daily: String = "temperature_2m_max,temperature_2m_min,uv_index_max,uv_index_clear_sky_max,precipitation_sum,wind_speed_10m_max,wind_gusts_10m_max,apparent_temperature_min,apparent_temperature_max,shortwave_radiation_sum,et0_fao_evapotranspiration,weathercode"
    ): WeatherResponse
}