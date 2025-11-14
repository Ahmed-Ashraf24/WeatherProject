package com.example.weatherproject.Data.Mapper.Mapper

import com.example.weatherproject.Data.Mapper.Utility.WeatherMapperUtils.filterHourly
import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly
import com.example.weatherproject.Data.Model.API.ResponseModel.HourlyUnits
import com.example.weatherproject.Domain.Entity.HourlyData
import com.example.weatherproject.Domain.Entity.HourlyDetail

object HourlyMapper {
    fun toHourlyData(hourly: Hourly, time: String, units: HourlyUnits): List<HourlyData> {
        val filteredHours = filterHourly(hourly, time)
        val hourlyList = mutableListOf<HourlyData>()
        filteredHours.time.forEachIndexed { index, hour ->
            hourlyList.add(
                HourlyData(
                    time = hour,
                    hourlyDetail = HourlyDetail(
                        apparent_temperature = "${filteredHours.apparent_temperature[index]} ${units.apparent_temperature}",
                        cloudcover = "${filteredHours.cloudcover[index]} ${units.cloudcover}",
                        precipitation = "${filteredHours.precipitation[index]} ${units.precipitation}",
                        pressure_msl = "${filteredHours.pressure_msl[index]} ${units.pressure_msl}",
                        relative_humidity_2m = "${filteredHours.relative_humidity_2m[index]} ${units.relative_humidity_2m}",
                        temperature_2m = "${filteredHours.temperature_2m[index]} ${units.temperature_2m}",
                        uv_index = "${filteredHours.uv_index[index]} ${units.uv_index}",
                        visibility = "${filteredHours.visibility[index]} ${units.visibility}",
                        weathercode = filteredHours.weathercode[index],
                        windspeed_10m = "${filteredHours.windspeed_10m[index]} ${units.windspeed_10m}"
                    )
                )
            )
        }
        return hourlyList
    }
}