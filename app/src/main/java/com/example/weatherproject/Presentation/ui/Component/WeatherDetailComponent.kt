package com.example.weatherproject.Presentation.ui.Component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.weatherproject.Presentation.UIModel.UIHourlyData
import com.example.weatherproject.Presentation.UIModel.UIHourlyDetail
import com.example.weatherproject.Presentation.UIModel.UIWeather
import com.example.weatherproject.Presentation.ui.theme.Typography
import com.example.weatherproject.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherDetailComponent(
    modifier: Modifier = Modifier,
    weather: UIWeather,
    isFav: Boolean,
    onFavClicked: () -> Unit,
    onListClicked: () -> Unit
) {

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
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                    Icon(
                        painterResource(R.drawable.outline_view_list_24),
                        "favList",
                        tint = Color.White,
                        modifier = Modifier.clickable { onListClicked() })

                Row(
                    Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        weather.date,
                        style = Typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier

                    )
                }

                    Icon(
                        if (isFav) painterResource(R.drawable.filled_favorite_ic)
                    else painterResource(R.drawable.outline_favorite_24),
                        modifier = Modifier.clickable {
                            onFavClicked()
                        },
                        contentDescription = "fav",
                        tint = Color.White
                    )

            }
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun WeatherDetailComponentPreview() {
    var isFav by remember { mutableStateOf(false) }
    WeatherDetailComponent(
        weather = UIWeather(
            "cinty", "temp", R.drawable.sun_clouds, "22", 15.2,
            listOf(
                UIHourlyData(
                    "223",
                    UIHourlyDetail(
                        "dasd",
                        "dasdw",
                        "ewqeqw",
                        "dsasx",
                        "dd",
                        "ssss",
                        "222",
                        "xx",
                        R.drawable.sun_clouds,
                        "ss",
                    )

                )
            ),
            condition = "sad",

            date = "wds",
            isMorning = true,
        ),
        isFav = isFav, onFavClicked = { isFav = !isFav },
        onListClicked = {}
    )

}