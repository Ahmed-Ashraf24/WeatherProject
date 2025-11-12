package com.example.weatherproject

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherapp.Utility.Conversion.ConversionUtilities.formatHour
import com.example.weatherapp.Utility.Conversion.ConversionUtilities.isDaytime
import com.example.weatherproject.Data.DataSource.RemoteSource.WeatherRetrofitClient
import com.example.weatherproject.Data.Mapper.WeatherMapper
import com.example.weatherproject.Data.Model.API.ResponseModel.Hourly
import com.example.weatherproject.Data.Repository.WeatherRepo
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.io.path.fileVisitor

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    runBlocking {
       try {
           val resp= WeatherRetrofitClient.api.getForecast(31.15,32.18)
           WeatherMapper.toForecastWeather(resp).forEach {
               println(it)
           }
       }catch (e:Exception){
           println(e)
       }

    }
}