package com.example.weatherproject.Presentation.ui.Component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.Utility.Constraints.IconConstraints
import com.example.weatherapp.Utility.Conversion.ConversionUtilities
import com.example.weatherproject.Domain.Entity.DailyWeather
import com.example.weatherproject.Presentation.UIModel.UIWeather
import com.example.weatherproject.Presentation.ui.theme.Typography
import com.example.weatherproject.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherDetailComponent(modifier: Modifier = Modifier,weather: UIWeather) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    )
    {
        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                weather.date,
                style = Typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(bottom = 26.dp)
            )
            Image(painter = painterResource(weather.weatherIconRes), "weather icon")
            Text(
                weather.city,
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                weather.temp,
                style = Typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 48.sp
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                weather.condition,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 10.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(top = 20.dp), thickness = 1.dp)


        }
    }
}