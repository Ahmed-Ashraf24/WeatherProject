package com.example.weatherproject.Data.Model.Room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.weatherproject.Data.Model.API.ResponseModel.Daily
import com.example.weatherproject.Data.Model.Room.Entity.DailyEntity
import com.example.weatherproject.Data.Model.Room.Entity.DailyWithHourly
import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity
import com.example.weatherproject.Data.Model.Room.Entity.ForecastEntity
import com.example.weatherproject.Data.Model.Room.Entity.ForecastWithHourly
import com.example.weatherproject.Data.Model.Room.Entity.HourlyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM Daily_Table")
    fun getCachedDaily(): Flow<DailyWithHourly>

    @Query("SELECT * FROM Forecast_Table")
    fun getCachedForecast(): Flow<List<ForecastWithHourly>>

    @Insert(onConflict = REPLACE)
    fun cacheDaily(daily: DailyEntity)

    @Insert(onConflict = REPLACE)
    fun cacheForecast(forecast: ForecastEntity)

    @Query("DELETE FROM Forecast_Table WHERE date < :today")
    fun cleanOldCachedWeather(today: String)

    @Insert(onConflict = REPLACE)
    suspend fun insertHourly(hourly: List<HourlyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteWeather(favWeather: FavWeatherEntity)


    @Delete
    suspend fun removeFavoriteWeather(favWeather: FavWeatherEntity)

    @Query("DELETE FROM fav_weather WHERE date = :date")
    suspend fun deleteFavoriteByDate(date: String)


    @Query("SELECT * FROM fav_weather ORDER BY date ASC")
    fun getAllFavoriteWeather(): Flow<List<FavWeatherEntity>>
}