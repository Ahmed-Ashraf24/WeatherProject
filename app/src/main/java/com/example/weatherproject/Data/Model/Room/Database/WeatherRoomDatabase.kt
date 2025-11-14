package com.example.weatherproject.Data.Model.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherproject.Data.Model.Room.DAO.WeatherDAO
import com.example.weatherproject.Data.Model.Room.Entity.DailyEntity
import com.example.weatherproject.Data.Model.Room.Entity.FavWeatherEntity
import com.example.weatherproject.Data.Model.Room.Entity.ForecastEntity
import com.example.weatherproject.Data.Model.Room.Entity.HourlyEntity

@Database(
    entities = [ForecastEntity::class, DailyEntity::class,
        HourlyEntity::class, FavWeatherEntity::class], version =3
)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO

    companion object {

        @Volatile
        var instance: WeatherRoomDatabase? = null
        fun getInstance(context: Context): WeatherRoomDatabase {
            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context,
                        WeatherRoomDatabase::class.java,
                        "WeatherDatabase"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}