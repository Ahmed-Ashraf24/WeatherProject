package com.example.weatherproject.Data.Repository


import com.example.weatherproject.Data.Mapper.WeatherMapper
import com.example.weatherproject.Data.Model.API.WeatherAPI
import com.example.weatherproject.Data.Model.API.WeatherData
import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Domain.IRepo.IWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepo(val api: WeatherAPI): IWeatherRepo {
    override fun getForecast(): Flow<WeatherData> = flow {
        while (true) {
            val result = api.getForecast(31.15,32.18)
            emit(WeatherData.DailyWeatherData(WeatherMapper.toEntityWeather(result)))
            emit(WeatherData.ForecastWeatherData(WeatherMapper.toForecastWeather(result)))

            delay(60 * 60 * 1000)
        }
    }.flowOn(Dispatchers.IO)
}
