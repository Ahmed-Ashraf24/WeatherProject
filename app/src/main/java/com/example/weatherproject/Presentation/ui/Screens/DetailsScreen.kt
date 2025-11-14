package com.example.weatherproject.Presentation.ui.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIWeather
import com.example.weatherproject.Presentation.UIModel.toFavWeather
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel
import com.example.weatherproject.Presentation.ui.Component.Grid
import com.example.weatherproject.Presentation.ui.Component.HourlyWeatherDetail
import com.example.weatherproject.Presentation.ui.Component.WeatherAttribute
import com.example.weatherproject.Presentation.ui.Component.WeatherDetailComponent
import com.example.weatherproject.Presentation.ui.theme.colorPalList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavController, weatherViewModel: WeatherViewModel,index:Int) {
    val weatherState = weatherViewModel.forecastData.collectAsState()
    var isFav by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = colorPalList,
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
        ) {
            item {
                weatherState.value?.get(index)?.let { weather ->
                    WeatherDetailComponent(
                        Modifier.padding(top = 38.dp), weather = weather.run {

                        UIWeather(
                            temp = weather.temp,
                            condition = weather.condition,
                            city = weather.city,
                            hourly = weather.hourly,
                            date = ConversionUtilities.getDayName(weather.date),
                            weatherIconRes = weather.weatherIconRes,
                            windSpeed = weather.windSpeed,
                            uv = 0.5,
                            isMorning = true,
                        )

                    }, isFav = isFav, onFavClicked = {
                            if (!isFav) {
                                weatherViewModel.addFavWeather(weather.toFavWeather())
                            }
                            else{
                                weatherViewModel.removeFavWeather(weather.toFavWeather())
                            }
                            isFav=!isFav

                    },
                        onListClicked = {navController.navigate("fav")}

                )
                }
            }
            item {
                LazyRow(Modifier.padding(vertical = 15.dp, horizontal = 10.dp)) {
                    items(24) {
                        HourlyWeatherDetail(
                            modifier = Modifier.padding(end = 30.dp),
                            time = weatherState.value?.get(index)?.hourly[it]?.time,
                            temp = weatherState.value?.get(index)?.hourly[it]?.hourlyDetail?.temperature_2m,
                            iconRes = weatherState.value?.get(index)?.hourly[it]?.hourlyDetail?.weatherIconRes
                        )
                    }
                }

            }

            item {
               weatherState.value?.get(index)?.let {   Grid(Modifier.padding(bottom = 15.dp),uiForecastWeather = it)}
            }
        }

    }
}

@Preview
@Composable
private fun DetailPreview() {
}