package com.example.weatherproject.Presentation.ui.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherproject.Presentation.UIModel.toFavWeather
import com.example.weatherproject.Presentation.UIModel.toUiFavWeather
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel
import com.example.weatherproject.Presentation.ui.Component.FavWeatherItem
import com.example.weatherproject.Presentation.ui.theme.Typography
import com.example.weatherproject.Presentation.ui.theme.colorPalList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavWeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    viewModel.refreshFavWeather()
    val favorites by viewModel.favWeather.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = colorPalList,
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            ).padding(vertical = 35.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
        ) {
            item{
                Row (Modifier.fillMaxWidth().padding(vertical =10.dp ), horizontalArrangement = Arrangement.Center){
                    Text("Favorite List", style = Typography.labelLarge.copy(color = Color.White, fontSize = 40.sp))
                }
            }
            favorites?.let{
                items(it.size) { index ->
                    val fav = it[index]

                    FavWeatherItem(
                        fav = fav.toUiFavWeather(),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 6.dp),
                        onClick = {
                            navController.navigate("detail/${index}")
                        },
                        onRemove = {
                            viewModel.removeFavWeather(fav.toFavWeather())
                        }
                    )
                }
            }
        }
    }
}

