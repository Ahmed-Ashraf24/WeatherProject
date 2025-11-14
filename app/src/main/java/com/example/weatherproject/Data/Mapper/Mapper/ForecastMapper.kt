package com.example.weatherproject.Data.Mapper.Mapper

import com.example.weatherproject.Data.Mapper.Utility.WeatherMapperUtils.filterHourly
import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly
import com.example.weatherproject.Data.Model.API.ResponseModel.HourlyUnits
import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Data.Model.Room.Entity.ForecastEntity
import com.example.weatherproject.Data.Model.Room.Entity.ForecastWithHourly
import com.example.weatherproject.Data.Model.Room.Entity.HourlyEntity
import com.example.weatherproject.Data.Model.Room.Entity.toHourlyData
import com.example.weatherproject.Domain.Entity.ForecastWeather
import com.example.weatherproject.Domain.Entity.HourlyDetail

object ForecastMapper {
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
                    hourly = HourlyMapper.toHourlyData(
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

    fun toForecastEntity(forecastWithHourly: ForecastWithHourly): ForecastWeather {
        return ForecastWeather(
            city = forecastWithHourly.forecast.city,
            temp = forecastWithHourly.forecast.temp,
            weatherCode = forecastWithHourly.forecast.weatherCode,
            windSpeed = forecastWithHourly.forecast.windSpeed,
            uv = forecastWithHourly.forecast.uv,
            uvClearSky = forecastWithHourly.forecast.uvClearSky,
            hourly = forecastWithHourly.hourlyList.map { it.toHourlyData() },
            date = forecastWithHourly.forecast.date,
            windGust = forecastWithHourly.forecast.windGust,
            precipitation = forecastWithHourly.forecast.precipitation,
            evapotranspiration = forecastWithHourly.forecast.evapotranspiration,
            apparentTemp = forecastWithHourly.forecast.apparentTemp,
            shortwaveRadiation = forecastWithHourly.forecast.shortwaveRadiation,
        )
    }
    fun toForecastEntity(weatherResponse: WeatherResponse): List<ForecastWithHourly> {
        val forecastList = mutableListOf<ForecastWithHourly>()
        weatherResponse.daily.time.forEachIndexed { index, date ->
            forecastList.add(
                ForecastWithHourly(
                    ForecastEntity(
                        city = weatherResponse.timezone,
                        temp = "${weatherResponse.daily.temperature_2m_min[index]}/${weatherResponse.daily.temperature_2m_max[index]} ${weatherResponse.current_weather_units.temperature}",
                        weatherCode = weatherResponse.daily.weathercode[index],
                        windSpeed = "${weatherResponse.daily.wind_speed_10m_max[index]} ${weatherResponse.daily_units.wind_speed_10m_max}",
                        uv = "${weatherResponse.daily.uv_index_max[index]} ${weatherResponse.daily_units.uv_index_max}",
                        uvClearSky = "${weatherResponse.daily.uv_index_clear_sky_max[index]} ${weatherResponse.daily_units.uv_index_clear_sky_max}",

                        date = date,
                        windGust = "${weatherResponse.daily.wind_gusts_10m_max[index]} ${weatherResponse.daily_units.wind_gusts_10m_max}",
                        precipitation = "${weatherResponse.daily.precipitation_sum[index]} ${weatherResponse.daily_units.precipitation_sum}",
                        evapotranspiration = "${weatherResponse.daily.et0_fao_evapotranspiration[index]} ${weatherResponse.daily_units.et0_fao_evapotranspiration}",
                        apparentTemp = "${weatherResponse.daily.apparent_temperature_min[index]} / ${weatherResponse.daily.apparent_temperature_max[index]} ${weatherResponse.daily_units.temperature_2m_max}",
                        shortwaveRadiation = "${weatherResponse.daily.shortwave_radiation_sum[index]} ${weatherResponse.daily_units.shortwave_radiation_sum}",
                    ),
                    hourlyList = toForecastHourlyEntity(
                        weatherResponse.hourly,
                        date,
                        weatherResponse.hourly_units
                    ),
                )
            )
        }
        return forecastList

    }
    fun toForecastHourlyEntity(hourly: Hourly, time: String, units: HourlyUnits): List<HourlyEntity> {
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
                    forecastDate = time,
                    dailyDate = null
                )
            )
        }
        return hourlyList
    }
}