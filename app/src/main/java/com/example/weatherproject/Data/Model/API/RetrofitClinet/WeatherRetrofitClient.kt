package com.example.weatherproject.Data.Model.API.RetrofitClinet

import com.example.weatherproject.Data.Model.API.service.WeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitClient{
    val api by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }
}