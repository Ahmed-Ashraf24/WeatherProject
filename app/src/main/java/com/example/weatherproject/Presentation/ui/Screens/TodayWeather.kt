package com.example.weatherproject.Presentation.ui.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Presentation.UIModel.UIWeather
import com.example.weatherproject.Presentation.UIModel.toFavWeather
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel
import com.example.weatherproject.Presentation.ui.Component.ForCastWeatherItem
import com.example.weatherproject.Presentation.ui.Component.HourlyWeatherDetail
import com.example.weatherproject.Presentation.ui.Component.WeatherDetailComponent
import com.example.weatherproject.Presentation.ui.theme.colorPalList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayScreen(navController: NavController,viewModel: WeatherViewModel) {
    val weatherState = viewModel.weatherData.collectAsState()
    val forecast = viewModel.forecastData.collectAsState()
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
        LazyColumn(Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)) {
            item {
                weatherState.value?.let { WeatherDetailComponent(Modifier.padding(top = 38.dp), weather = it.copy(date = "Today"), isFav = isFav,
                    onFavClicked = {
                    if (!isFav) {
                    viewModel.addFavWeather(it.toFavWeather())
                    }
                    else{
                    viewModel.removeFavWeather(it.toFavWeather())
                    }
                    isFav=!isFav

                },
                    onListClicked = {navController.navigate("fav")}) }
            }
            item {
                LazyRow(Modifier.padding(vertical = 15.dp, horizontal = 10.dp)) {
                    items(24) {
                        HourlyWeatherDetail(modifier = Modifier.padding(end = 30.dp),
                            time = weatherState.value?.hourly[it]?.time,
                            temp = weatherState.value?.hourly[it]?.hourlyDetail?.temperature_2m,
                            iconRes = weatherState.value?.hourly[it]?.hourlyDetail?.weatherIconRes)
                    }
                }

            }
            items(7) {
                ForCastWeatherItem(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 10.dp
                    ).clickable{navController.navigate("detail/${it}")}
                , forecast.value?.get(it)
                )

            }

            }

        }

    }
