package com.example.weatherproject.Data.Mapper

import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly
import com.example.weatherproject.Data.Model.API.ResponseModel.HourlyUnits
import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Domain.Entity.ForecastWeather
import com.example.weatherproject.Domain.Entity.HourlyData
import com.example.weatherproject.Domain.Entity.HourlyDetail
import kotlin.collections.filter
import kotlin.text.contains

object WeatherMapper {
    fun toForecastWeather(weatherResponse: WeatherResponse): List<ForecastWeather> {
        val forecastList = mutableListOf<ForecastWeather>()
        weatherResponse.daily.time.forEachIndexed { index, date ->
            forecastList.add(
                ForecastWeather(
                    city = weatherResponse.timezone,
                    temp = "${weatherResponse.daily.temperature_2m_min[index]}/${weatherResponse.daily.temperature_2m_max[index]} ${weatherResponse.current_weather_units.temperature}",
                    weatherCode = weatherResponse.daily.weathercode[index],
                    windSpeed = "${weatherResponse.daily.wind_speed_10m_max[index]} ${weatherResponse.daily_units.wind_speed_10m_max}",
                    uv = "${weatherResponse.daily.uv_index_max[index]} ${weatherResponse.daily_units.uv_index_max}",
                    uvClearSky = "${weatherResponse.daily.uv_index_clear_sky_max[index]} ${weatherResponse.daily_units.uv_index_clear_sky_max}",
                    hourly = toHourlyData(
                        weatherResponse.hourly,
                        date,
                        weatherResponse.hourly_units
                    ),
                    date = date,
                    windGust = "${weatherResponse.daily.wind_gusts_10m_max[index]} ${weatherResponse.daily_units.wind_gusts_10m_max}",
                    precipitation = "${weatherResponse.daily.precipitation_sum[index]} ${weatherResponse.daily_units.precipitation_sum}",
                    evapotranspiration = "${weatherResponse.daily.et0_fao_evapotranspiration[index]} ${weatherResponse.daily_units.et0_fao_evapotranspiration}",
                    apparentTemp = "${weatherResponse.daily.apparent_temperature_min[index]} / ${weatherResponse.daily.apparent_temperature_max[index]} ${weatherResponse.daily_units.temperature_2m_max}",
                    shortwaveRadiation = "${weatherResponse.daily.shortwave_radiation_sum[index]} ${weatherResponse.daily_units.shortwave_radiation_sum}",
                )
            )
        }
        return forecastList

    }

    fun toEntityWeather(weatherResponse: WeatherResponse): DailyWeather {

        return DailyWeather(
            city = weatherResponse.timezone,
            temp = "${weatherResponse.current_weather.temperature} ${weatherResponse.current_weather_units.temperature}",
            weatherCode = weatherResponse.current_weather.weathercode,
            windSpeed = "${weatherResponse.current_weather.temperature} ${weatherResponse.current_weather_units.temperature}",
            uv = weatherResponse.daily.uv_index_max[0],
            hourly = toHourlyData(
                weatherResponse.hourly,
                weatherResponse.daily.time[0],
                weatherResponse.hourly_units
            ),
            date = weatherResponse.daily.time[0].trim(),
            isMorning = weatherResponse.current_weather.is_day == 1
        )

    }

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


    private fun filterHourly(hourly: Hourly, timeFormat: String): Hourly {
        return hourly.run {
            val timesForToday = time.filter { it.contains(timeFormat) }
            val sliceingRange = 0..timesForToday.size
            Hourly(
                apparent_temperature = apparent_temperature.slice(sliceingRange),
                cloudcover = cloudcover.slice(sliceingRange),
                precipitation = precipitation.slice(sliceingRange),
                pressure_msl = pressure_msl.slice(sliceingRange),
                relative_humidity_2m = relative_humidity_2m.slice(sliceingRange),
                temperature_2m = temperature_2m.slice(sliceingRange),
                time = timesForToday,
                uv_index = uv_index.slice(sliceingRange),
                visibility = visibility.slice(sliceingRange),
                weathercode = weathercode.slice(sliceingRange),
                windspeed_10m = windspeed_10m.slice(sliceingRange),
            )
        }
    }
}