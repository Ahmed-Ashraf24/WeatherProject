package com.example.weatherproject.Presentation.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherproject.Data.DataSource.RemoteSource.WeatherRetrofitClient
import com.example.weatherproject.Data.Mapper.WeatherMapper
import com.example.weatherproject.Data.Model.API.WeatherData
import com.example.weatherproject.Data.Repository.WeatherRepo
import com.example.weatherproject.Domain.Entity.toUIForecastWeather
import com.example.weatherproject.Domain.Entity.toUIWeather
import com.example.weatherproject.Presentation.UIModel.UIForecastWeather
import com.example.weatherproject.Presentation.UIModel.UIWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel: ViewModel() {

    private val _weatherData =MutableStateFlow<UIWeather?>(null)
    val weatherData: StateFlow<UIWeather?> = _weatherData
    private val _forecastData =MutableStateFlow<List<UIForecastWeather>?>(null)
    val forecastData: StateFlow<List<UIForecastWeather>?> = _forecastData
    init {
        getWeather()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeather(){
        viewModelScope.launch {
           WeatherRepo(WeatherRetrofitClient.api).getForecast().collect {weatherData->
               when(weatherData){
                   is WeatherData.DailyWeatherData -> _weatherData.value=weatherData.daily.toUIWeather()
                   is WeatherData.ForecastWeatherData->_forecastData.value=weatherData.forecast.map { it.toUIForecastWeather() }
               }

           }

        }

    }
}