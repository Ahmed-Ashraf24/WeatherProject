package com.example.weatherapp.Utility.Conversion

import android.icu.text.DateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object ConversionUtilities {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatHour(localTime: String?): String {
        return try {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
            val dateTime = LocalDateTime.parse(localTime ?: "2025-11-10T00:00", inputFormatter)
            val outputFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            dateTime.format(outputFormatter)
                .uppercase(Locale.ENGLISH) // ensure AM/PM matches parser
        } catch (e: Exception) {
            e.printStackTrace()
            "12:00 AM"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isDaytime(timeString: String): Boolean {
        return try {
            val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            val time = LocalTime.parse(timeString.trim().uppercase(Locale.ENGLISH), formatter)
            val startDay = LocalTime.of(6, 0)
            val endDay = LocalTime.of(18, 0)
            Log.d(
                "checkingtime", "time: $timeString isDay : ${
                    time in startDay..endDay
                }"
            )
            time in startDay..endDay

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayName(localTime: String?): String {
        val date = LocalDate.parse(localTime ?: "2025-11-10")
        val dayOfWeek = date.dayOfWeek
        val dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        return dayName
    }


}