package com.example.weatherproject.Data.Mapper.Utility

import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly

object WeatherMapperUtils {
    fun filterHourly(hourly: Hourly, day: String): Hourly {
        val timesForDay = hourly.time.filter { it.contains(day) }

        val sliceRange = 0 until timesForDay.size

        return Hourly(
            apparent_temperature = hourly.apparent_temperature.slice(sliceRange),
            cloudcover = hourly.cloudcover.slice(sliceRange),
            precipitation = hourly.precipitation.slice(sliceRange),
            pressure_msl = hourly.pressure_msl.slice(sliceRange),
            relative_humidity_2m = hourly.relative_humidity_2m.slice(sliceRange),
            temperature_2m = hourly.temperature_2m.slice(sliceRange),
            time = timesForDay,
            uv_index = hourly.uv_index.slice(sliceRange),
            visibility = hourly.visibility.slice(sliceRange),
            weathercode = hourly.weathercode.slice(sliceRange),
            windspeed_10m = hourly.windspeed_10m.slice(sliceRange),
        )
    }
}