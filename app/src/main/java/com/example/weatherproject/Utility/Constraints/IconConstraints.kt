package com.example.weatherapp.Utility.Constraints

import com.example.weatherproject.R

object IconConstraints {
    private val weatherCodeMap = mapOf(
        0 to Pair(R.drawable.sun, R.drawable.moon),
        1 to Pair(R.drawable.sun, R.drawable.moon),
        2 to Pair(R.drawable.sun_clouds, R.drawable.moon_clouds),
        3 to Pair(R.drawable.cloudy, R.drawable.overcast),
        45 to Pair(R.drawable.fog, R.drawable.fog),
        48 to Pair(R.drawable.fog, R.drawable.fog),
        51 to Pair(R.drawable.rain, R.drawable.moon_and_rain),
        53 to Pair(R.drawable.rain, R.drawable.moon_and_rain),
        55 to Pair(R.drawable.rain, R.drawable.moon_and_rain),
        61 to Pair(R.drawable.sun_rain, R.drawable.moon_and_rain),
        63 to Pair(R.drawable.rain, R.drawable.moon_and_rain),
        65 to Pair(R.drawable.rain, R.drawable.moon_and_rain),
        71 to Pair(R.drawable.snow, R.drawable.snow),
        73 to Pair(R.drawable.snow, R.drawable.snow),
        75 to Pair(R.drawable.snow, R.drawable.snow),
        80 to Pair(R.drawable.sun_rain, R.drawable.moon_and_rain),
        81 to Pair(R.drawable.sun_rain, R.drawable.moon_and_rain),
        82 to Pair(R.drawable.sun_rain, R.drawable.moon_and_rain),
        95 to Pair(R.drawable.thunder, R.drawable.thunder),
        96 to Pair(R.drawable.thunder_snow, R.drawable.thunder_snow),
        99 to Pair(R.drawable.thunderstorm_snow, R.drawable.thunderstorm_snow)
    )

    private val codeMap = mapOf(
        0 to "Clear sky",
        1 to "Mainly clear",
        2 to "Partly cloudy",
        3 to "Overcast",
        45 to "Fog",
        48 to "Depositing rime fog",
        51 to "Light drizzle",
        53 to "Moderate drizzle",
        55 to "Dense drizzle",
        56 to "Light freezing drizzle",
        57 to "Dense freezing drizzle",
        61 to "Slight rain",
        63 to "Moderate rain",
        65 to "Heavy rain",
        66 to "Light freezing rain",
        67 to "Heavy freezing rain",
        71 to "Slight snow fall",
        73 to "Moderate snow fall",
        75 to "Heavy snow fall",
        77 to "Snow grains",
        80 to "Slight rain showers",
        81 to "Moderate rain showers",
        82 to "Violent rain showers",
        85 to "Slight snow showers",
        86 to "Heavy snow showers",
        95 to "Thunderstorm",
        96 to "Thunderstorm with slight hail",
        99 to "Thunderstorm with heavy hail"
    )

    fun getWeatherIcon(condition: Int, isDaytime: Boolean = true): Int {
        val icons = weatherCodeMap[condition]
        return when {
            icons != null -> if (isDaytime) icons.first else icons.second
            isDaytime -> R.drawable.sun_clouds
            else -> R.drawable.moon_clouds
        }
    }

    fun getDescription(code: Int): String {
        return codeMap[code] ?: "Unknown weather"
    }
}