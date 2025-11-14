package com.example.weatherproject.Data.Model.Room.Entity

data class HourlyDetailEntity(    val apparent_temperature: String,
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
