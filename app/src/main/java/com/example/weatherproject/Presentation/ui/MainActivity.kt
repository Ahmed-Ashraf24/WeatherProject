package com.example.weatherproject.Presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel
import com.example.weatherproject.Presentation.ui.Screens.DetailsScreen
import com.example.weatherproject.Presentation.ui.Screens.TodayScreen
import com.example.weatherproject.Presentation.ui.theme.WeatherProjectTheme

class MainActivity : ComponentActivity() {
    val weatherViewModel: WeatherViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,"home"){
                composable("home"){
                    TodayScreen(navController,weatherViewModel)
                }
                composable("detail/{index}"){ backStackEntry ->
                    val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                    DetailsScreen(navController,weatherViewModel,index?:0)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherProjectTheme {
        Greeting("Android")
    }
}