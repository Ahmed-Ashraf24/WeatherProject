package com.example.weatherproject.Presentation.UIModel

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
