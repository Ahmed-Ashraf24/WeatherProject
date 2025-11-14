package com.example.weatherproject.Data.DataSource.LocalSource.Room

import com.example.weatherproject.Data.DataSource.LocalSource.WeatherCachingClint
import com.example.weatherproject.Data.Mapper.Mapper.DailyWeatherMapper
import com.example.weatherproject.Data.Mapper.Mapper.ForecastMapper
import com.example.weatherproject.Data.Model.API.ResponseModel.WeatherResponse
import com.example.weatherproject.Domain.Entity.WeatherData
import com.example.weatherproject.Data.Model.Room.Database.WeatherRoomDatabase
import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity
import com.example.weatherproject.WeatherApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class RoomWeatherDataSource() : WeatherCachingClint {
    private val roomWeather = WeatherRoomDatabase.Companion.getInstance(WeatherApplication().getContext())
    override suspend fun cacheWeather(weatherResponse: WeatherResponse) {
        val dailyEntity = DailyWeatherMapper.toDailyWithHourlyEntity(weatherResponse)
        val forecastEntities = ForecastMapper.toForecastEntity(weatherResponse)
        roomWeather.weatherDao().apply {
            cleanOldCachedWeather(dailyEntity.daily.date)
            cacheDaily(dailyEntity.daily)
            insertHourly(dailyEntity.hourlyList)

            forecastEntities.forEach {
                cacheForecast(it.forecast)
                insertHourly(it.hourlyList)
            }
        }


    }

    override fun getCachedWeather(): Flow<WeatherData> = flow {
        roomWeather.weatherDao().getCachedDaily()
            .combine(roomWeather.weatherDao().getCachedForecast()) { daily, forecast ->
                WeatherData.CombinedWeatherData(
                    DailyWeatherMapper.toDailyWeather(daily), forecast.map(
                        ForecastMapper::toForecastEntity
                    )
                )
            }
            .collect { combined ->
                emit(combined)
            }


    }

    override suspend fun addFavWeather(favWeather: FavWeatherEntity) {
        roomWeather.weatherDao().addFavoriteWeather(favWeather)
    }

    override suspend fun removeFavWeather(favWeather: FavWeatherEntity) {
        roomWeather.weatherDao().removeFavoriteWeather(favWeather)
    }

    override fun getFavWeatherList(): Flow<List<FavWeatherEntity>> =flow {
        roomWeather.weatherDao().getAllFavoriteWeather().collect { favWeatherEntities ->
            emit(favWeatherEntities)
        }
    }
}