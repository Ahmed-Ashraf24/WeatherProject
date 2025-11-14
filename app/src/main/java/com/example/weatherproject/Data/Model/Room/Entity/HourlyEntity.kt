package com.example.weatherproject.Data.Model.Room.Entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.weatherproject.Domain.Entity.HourlyData
import com.example.weatherproject.Domain.Entity.HourlyDetail

@Entity(
    tableName = "Hourly_Table",
    foreignKeys = [
        ForeignKey(
            entity = ForecastEntity::class,
            parentColumns = ["date"],
            childColumns = ["forecastDate"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HourlyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time:String,
    val forecastDate: String?,
    val dailyDate: String?,


    @Embedded
    val hourlyDetail: HourlyDetail

)
fun HourlyEntity.toHourlyData(): HourlyData {
    return HourlyData(time=this.time,hourlyDetail=hourlyDetail)
}
