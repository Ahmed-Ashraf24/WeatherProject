package com.example.weatherproject.Presentation.ui.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherproject.Presentation.UIModel.UIWeather
import com.example.weatherproject.Presentation.UIModel.UiFavWeather
import com.example.weatherproject.R


@Composable
fun FavWeatherItem(
    modifier: Modifier = Modifier,
    fav: UiFavWeather,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.1f))
            .padding(14.dp)
            .clickable { onClick() }
            .fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = fav.weatherIconRes),
            contentDescription = null,
            modifier = Modifier
                .height(40.dp)
                .padding(end = 12.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = fav.city ?: "Unknown location",
                color = Color.White
            )
            Text(
                text = fav.condition ,
                color = Color.White.copy(alpha = 0.8f)
            )
            Text(
                text = fav.date ,
                color = Color.White.copy(alpha = 0.8f)
            )
            Text(
                text = "${fav.temp} °C",
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

       Icon(
            painter = painterResource(R.drawable.filled_favorite_ic),
            contentDescription = "Remove favorite",
            tint = Color.White,
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable { onRemove() }
        )
    }
}
