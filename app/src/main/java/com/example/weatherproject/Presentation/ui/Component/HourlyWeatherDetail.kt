package com.example.weatherproject.Presentation.ui.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherproject.R
import java.sql.Time

@Preview
@Composable
fun HourlyWeatherDetail(modifier: Modifier = Modifier,time: String?,temp:String?,iconRes:Int?) {
    Column(modifier,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(time?:"time", color = Color.White, fontSize = 14.sp)
        Image(modifier = Modifier.size(40.dp),painter = painterResource(iconRes?:R.drawable.sun_clouds), contentDescription = "icon")
        Text(temp?:"temp", color = Color.White, fontSize = 16.sp)
    }

}