package com.example.weatherproject.Domain.Entity

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.Utility.Constraints.IconConstraints
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIWeather

data class DailyWeather(
    val city: String,
    val temp: String,
    val weatherCode: Int,
    val windSpeed: String,
    val uv: Double,
    val hourly: List<HourlyData>,
    val date: String,
    val isMorning: Boolean
)
@RequiresApi(Build.VERSION_CODES.O)
fun DailyWeather.toUIWeather(): UIWeather{
    return UIWeather(
        city = this.city,
        temp = this.temp,
        weatherIconRes = IconConstraints.getWeatherIcon(weatherCode,this.isMorning),
        windSpeed = this.windSpeed,
        uv = this.uv,
        hourly = this.hourly.map { it.toUIHourlyData() },
        date = ConversionUtilities.getDayName(this.date),
        condition = IconConstraints.getDescription(weatherCode),
        isMorning = this.isMorning
    )
}
