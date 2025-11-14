package com.example.weatherproject.Presentation.ui.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherproject.Presentation.ui.theme.Typography
import com.example.weatherproject.Presentation.ui.theme.colorPalList

@Composable
fun WeatherAttribute(modifier: Modifier = Modifier,label:String,value:String) {
    Surface(modifier,color = Color.Black.copy(alpha = .8f), shape = RoundedCornerShape(10.dp)) {
        Column(
            Modifier.padding(12.dp),

            horizontalAlignment = Alignment.Start
        ) {
            Text(label, style = Typography.labelMedium.copy(fontSize = 18.sp), color = Color.White.copy(alpha = .6f), modifier = Modifier.padding(bottom = 5.dp))
            Text(value, style = Typography.labelMedium.copy(fontSize = 18.sp),color = Color.White)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WeatherAttributePrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors = colorPalList))
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 153.dp),
            columns = GridCells.Fixed(2)
          ) {

            items(5) {
                WeatherAttribute(modifier = Modifier.padding(3.dp), label = "label","value")
            }
        }
    }
}