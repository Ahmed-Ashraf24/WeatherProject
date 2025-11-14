package com.example.weatherproject.Data.Repository


import com.example.weatherproject.Data.DataSource.LocalSource.WeatherCachingClint
import com.example.weatherproject.Data.DataSource.RemoteSource.RemoteWeatherClient
import com.example.weatherproject.Data.Mapper.Mapper.FavWeatherMapper
import com.example.weatherproject.Domain.Entity.FavWeather
import com.example.weatherproject.Domain.Entity.WeatherData
import com.example.weatherproject.Domain.Entity.toFavWeatherEntity
import com.example.weatherproject.Domain.IRepo.IWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepo(val remoteSource: RemoteWeatherClient,val localDatabase: WeatherCachingClint): IWeatherRepo {
    override fun getForecast(lat:Double,lon: Double): Flow<WeatherData> = flow {
        while (true) {
            try {
                val result = remoteSource.getWeather(lat,lon)
                localDatabase.cacheWeather(result)

            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                localDatabase.getCachedWeather().collect { weatherData ->
                    emit(weatherData)
                }
            }

            delay(60 * 60 * 1000)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun addToFav(favWeather: FavWeather) {
        localDatabase.addFavWeather(favWeather.toFavWeatherEntity())
    }

    override suspend fun removeToFav(favWeather: FavWeather) {
        localDatabase.removeFavWeather(favWeather.toFavWeatherEntity())
    }

    override fun getFavWeatherList(): Flow<List<FavWeather>> =flow {
        localDatabase.getFavWeatherList().collect { favWeatherEntities ->
            emit(favWeatherEntities.map (FavWeatherMapper::toFavWeather))
        }
    }
}
