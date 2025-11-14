package com.example.weatherproject.Presentation.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherproject.Data.DataSource.LocalSource.Room.RoomWeatherDataSource
import com.example.weatherproject.Data.DataSource.RemoteSource.OpenMeteoAPI.OpenMeteoWeather
import com.example.weatherproject.Domain.Entity.WeatherData
import com.example.weatherproject.Data.Repository.WeatherRepo
import com.example.weatherproject.Domain.Entity.FavWeather
import com.example.weatherproject.Domain.Entity.toUIForecastWeather
import com.example.weatherproject.Domain.Entity.toUIWeather
import com.example.weatherproject.Presentation.UIModel.UIForecastWeather
import com.example.weatherproject.Presentation.UIModel.UIWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(): ViewModel() {

    private val _weatherData =MutableStateFlow<UIWeather?>(null)
    val weatherData: StateFlow<UIWeather?> = _weatherData
    private val _forecastData =MutableStateFlow<List<UIForecastWeather>?>(null)
    val forecastData: StateFlow<List<UIForecastWeather>?> = _forecastData
    private val _favWeather =MutableStateFlow<List<UIForecastWeather>?>(null)
    val favWeather: StateFlow<List<UIForecastWeather>?> = _favWeather
    init {
        getWeather()
        refreshFavWeather()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeather(lat:Double=31.15, lon:Double=32.18){
        viewModelScope.launch {
           WeatherRepo( remoteSource = OpenMeteoWeather(),
               RoomWeatherDataSource()).getForecast(lat,lon).collect { weatherData->
               when(weatherData){
                   is WeatherData.DailyWeatherData -> _weatherData.value=weatherData.daily.toUIWeather()
                   is WeatherData.ForecastWeatherData->_forecastData.value=weatherData.forecast.map { it.toUIForecastWeather() }
                   is WeatherData.CombinedWeatherData ->{
                       _weatherData.value=weatherData.daily.toUIWeather()
                       _forecastData.value=weatherData.forecast.map { it.toUIForecastWeather() }
                   }
               }

           }

        }

    }
    fun addFavWeather(favWeather: FavWeather){
        viewModelScope.launch {
            WeatherRepo( remoteSource = OpenMeteoWeather(),
                RoomWeatherDataSource()).addToFav(favWeather)


            }

        }
    fun removeFavWeather(favWeather: FavWeather){
        viewModelScope.launch {
            WeatherRepo( remoteSource = OpenMeteoWeather(),
                RoomWeatherDataSource()).removeToFav(favWeather)
            refreshFavWeather()

        }

    }

    fun refreshFavWeather(){
        viewModelScope.launch {
            WeatherRepo( remoteSource = OpenMeteoWeather(),
                RoomWeatherDataSource()).getFavWeatherList().collect {
                    favWeathers ->
                    _favWeather.value=_forecastData.value?.filter {forecast-> favWeathers.any{
                        it.date==forecast.date
                    } }
            }

        }

    }

    }
