package com.example.weatherproject.Presentation.ui.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherproject.Presentation.UIModel.UIForecastWeather
import com.example.weatherproject.R
@Composable
fun ForCastWeatherItem(modifier: Modifier = Modifier,uiForecastWeather: UIForecastWeather?) {
    Row(modifier.background(Color.White.copy(alpha = .3f)).padding(vertical = 10.dp,horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(uiForecastWeather?.date?:"day", color = Color.White, modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f).padding(end = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(uiForecastWeather?.weatherIconRes?:R.drawable.sun_clouds),
                modifier = Modifier.size(24.dp),
                contentDescription = "ic"
            )
            Text(uiForecastWeather?.condition?:"details", color = Color.White, modifier = Modifier.weight(1f))
        }
        Text(uiForecastWeather?.temp?:"temp", color = Color.White)
    }
}