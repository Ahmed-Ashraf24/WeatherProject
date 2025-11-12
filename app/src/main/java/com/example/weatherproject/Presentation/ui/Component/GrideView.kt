package com.example.weatherproject.Presentation.ui.Component

import android.widget.GridView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherproject.Presentation.UIModel.UIForecastWeather

@Composable
fun Grid(modifier: Modifier = Modifier, uiForecastWeather: UIForecastWeather) {
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Row(Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            WeatherAttribute(
                Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                "wind speed",
                value = uiForecastWeather.windSpeed
            )
            WeatherAttribute(Modifier.weight(1f), "wind gusts", value = uiForecastWeather.windGust)

        }
        Row(Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            WeatherAttribute(
                Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                "uv",
                value = uiForecastWeather.uv
            )
            WeatherAttribute(Modifier.weight(1f), "uv clearsky", value = uiForecastWeather.uvClearSky)

        }
        Row(Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            WeatherAttribute(
                Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                "apparent tempreture",
                value = uiForecastWeather.apparentTemp
            )
            WeatherAttribute(Modifier.weight(1f), "shortwave Radiation", value = uiForecastWeather.shortwaveRadiation)

        }
        Row(Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            WeatherAttribute(
                Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                "evapotranspiration",
                value = uiForecastWeather.evapotranspiration
            )
            WeatherAttribute(Modifier.weight(1f), "precipitation", value = uiForecastWeather.precipitation)

        }
    }
}