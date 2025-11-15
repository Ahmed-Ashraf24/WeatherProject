package com.example.weatherproject.Presentation.ViewModel.Factory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherproject.Domain.IRepo.IWeatherRepo
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(private val repo: IWeatherRepo): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(repo) as T
        }

        return super.create(modelClass)
    }
}