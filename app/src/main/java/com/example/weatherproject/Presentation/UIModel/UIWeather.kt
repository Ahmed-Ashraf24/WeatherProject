package com.example.weatherproject.Presentation.UIModel

import com.example.weatherproject.Domain.Entity.FavWeather

data class UIWeather(
    val city: String,
    val temp: String,
    val weatherIconRes:Int,
    val windSpeed: String,
    val uv: Double,
    val hourly: List<UIHourlyData>,
    val condition:String,
    val date:String,
    val isMorning: Boolean,
)
fun UIWeather.toFavWeather(): FavWeather{
    return FavWeather(this.date,this.condition,this.weatherIconRes,this.temp)
}
fun UIWeather.toUiFavWeather(): UiFavWeather{
    return UiFavWeather(city=this.city,date=this.date, condition = this.condition, weatherIconRes = this.weatherIconRes, temp = this.temp)
}