package com.example.weatherproject

import android.app.Application
import android.content.Context

class WeatherApplication: Application() {
    companion object {
        private var weatherAppInstance: WeatherApplication?=null
        }

    fun getContext(): Context {
        return weatherAppInstance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        weatherAppInstance=this
    }

}