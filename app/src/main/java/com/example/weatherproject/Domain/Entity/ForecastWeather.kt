package com.example.weatherproject.Domain.Entity

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.Utility.Constraints.IconConstraints
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIForecastWeather
import com.example.weatherproject.Presentation.UIModel.UIWeather

data class ForecastWeather(
    val city: String,
    val temp: String,
    val weatherCode: Int,
    val windSpeed: String,
    val precipitation:String,
    val uv: String,
    val uvClearSky:String,
    val hourly: List<HourlyData>,
    val date: String,
    val apparentTemp:String,
    val windGust:String,
    val shortwaveRadiation:String,
    val evapotranspiration:String
    )
@RequiresApi(Build.VERSION_CODES.O)
fun ForecastWeather.toUIForecastWeather(): UIForecastWeather {
    return UIForecastWeather(
        city = this.city,
        temp = this.temp,
        weatherIconRes = IconConstraints.getWeatherIcon(weatherCode),
        windSpeed = this.windSpeed,
        uv = this.uv,
        hourly = this.hourly.map { it.toUIHourlyData() },
        date = ConversionUtilities.getDayName(this.date),
        condition = IconConstraints.getDescription(weatherCode),
        windGust = this.windGust,
        apparentTemp = this.apparentTemp,
        shortwaveRadiation = this.shortwaveRadiation,
        evapotranspiration = this.evapotranspiration,
        uvClearSky = this.uvClearSky,
        precipitation = this.precipitation
    )

}
