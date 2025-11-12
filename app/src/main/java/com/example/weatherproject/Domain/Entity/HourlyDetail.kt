package com.example.weatherproject.Domain.Entity

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.Utility.Constraints.IconConstraints
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIHourlyData
import com.example.weatherproject.Presentation.UIModel.UIHourlyDetail

data class HourlyDetail(
    val apparent_temperature: String,
    val cloudcover: String,
    val precipitation: String,
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val uv_index: String,
    val visibility: String,
    val weathercode: Int,
    val windspeed_10m: String
)
@RequiresApi(Build.VERSION_CODES.O)
fun HourlyDetail.toUIHourlyDetail(time:String): UIHourlyDetail{
    return UIHourlyDetail(
        apparent_temperature = this.temperature_2m,
        cloudcover = this.cloudcover,
        precipitation = this.precipitation,
        pressure_msl = this.pressure_msl,
        relative_humidity_2m = this.relative_humidity_2m,
        temperature_2m = this.temperature_2m,
        uv_index = this.uv_index,
        visibility = this.visibility,
        weatherIconRes = IconConstraints.getWeatherIcon(this.weathercode, ConversionUtilities.isDaytime(time)),
        windspeed_10m = this.windspeed_10m
    )
}