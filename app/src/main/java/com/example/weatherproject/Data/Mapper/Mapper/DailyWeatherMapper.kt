package com.example.weatherproject.Data.Mapper.Mapper

import com.example.weatherproject.Data.Mapper.Utility.WeatherMapperUtils.filterHourly
import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly
import com.example.weatherproject.Data.Model.API.ResponseModel.HourlyUnits
import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Data.Model.Room.Entity.DailyEntity
import com.example.weatherproject.Data.Model.Room.Entity.DailyWithHourly
import com.example.weatherproject.Data.Model.Room.Entity.HourlyEntity
import com.example.weatherproject.Data.Model.Room.Entity.toHourlyData
import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Domain.Entity.HourlyDetail

object DailyWeatherMapper {


    fun toDailyWithHourlyEntity(weatherResponse: WeatherResponse): DailyWithHourly {

        return DailyWithHourly(
            DailyEntity(
                city = weatherResponse.timezone,
                temp = "${weatherResponse.current_weather.temperature} ${weatherResponse.current_weather_units.temperature}",
                weatherCode = weatherResponse.current_weather.weathercode,
                windSpeed = "${weatherResponse.current_weather.temperature} ${weatherResponse.current_weather_units.temperature}",
                uv = weatherResponse.daily.uv_index_max[0],
                date = weatherResponse.daily.time[0].trim(),
                isMorning = weatherResponse.current_weather.is_day == 1,
            ),
            hourlyList = toDailyHourlyEntity(
                weatherResponse.hourly,
                weatherResponse.daily.time[0],
                weatherResponse.hourly_units
            )
        )

    }
    fun toDailyWeather(weatherEntity: DailyWithHourly): DailyWeather {

        return DailyWeather(
            city = weatherEntity.daily.city,
            temp = weatherEntity.daily.temp,
            weatherCode = weatherEntity.daily.weatherCode,
            windSpeed = weatherEntity.daily.windSpeed,
            uv = weatherEntity.daily.uv,
            hourly = weatherEntity.hourlyList.map { it.toHourlyData() },
            date = weatherEntity.daily.date,
            isMorning = weatherEntity.daily.isMorning
        )

    }


    fun toDailyHourlyEntity(hourly: Hourly, time: String, units: HourlyUnits): List<HourlyEntity> {
        val filteredHours = filterHourly(hourly, time)
        val hourlyList = mutableListOf<HourlyEntity>()
        filteredHours.time.forEachIndexed { index, hour ->
            hourlyList.add(
                HourlyEntity(
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
                    ),
                    forecastDate = null,
                    dailyDate = time
                )
            )
        }
        return hourlyList
    }





}