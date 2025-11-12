package com.example.weatherproject.Domain.Entity

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIHourlyData

data class HourlyData (
    val time:String,
    val hourlyDetail: HourlyDetail
)
@RequiresApi(Build.VERSION_CODES.O)
fun HourlyData.toUIHourlyData(): UIHourlyData{
  return  UIHourlyData(
        time = ConversionUtilities.formatHour(this.time),
        hourlyDetail = hourlyDetail.toUIHourlyDetail(ConversionUtilities.formatHour(this.time))
    )
}
