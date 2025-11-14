package com.example.weatherproject.Presentation.UIModel

import com.example.weatherproject.Domain.Entity.FavWeather

data class UIForecastWeather(
    val city: String,
    val temp: String,
    val weatherIconRes:Int,
    val windSpeed: String,
    val uv: String,
    val uvClearSky:String,
    val apparentTemp:String,
    val windGust:String,
    val shortwaveRadiation:String,
    val evapotranspiration:String,
    val precipitation:String,

    val hourly: List<UIHourlyData>,
    val condition:String,
    val date:String,
)
fun UIForecastWeather.toFavWeather(): FavWeather{
    return FavWeather(this.date,this.condition,this.weatherIconRes,this.temp)
}
fun UIForecastWeather.toUiFavWeather(): UiFavWeather{
    return UiFavWeather(city = this.city,date=this.date, condition = this.condition, weatherIconRes = this.weatherIconRes, temp = this.temp)
}
