package com.example.weatherproject.Presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherproject.Data.DataSource.LocalSource.Room.RoomWeatherDataSource
import com.example.weatherproject.Data.DataSource.RemoteSource.OpenMeteoAPI.OpenMeteoWeather
import com.example.weatherproject.Data.Repository.WeatherRepo
import com.example.weatherproject.Presentation.ViewModel.WeatherViewModel
import com.example.weatherproject.Presentation.ViewModel.Fatory.WeatherViewModelFactory
import com.example.weatherproject.Presentation.ui.Screens.DetailsScreen
import com.example.weatherproject.Presentation.ui.Screens.FavWeatherScreen
import com.example.weatherproject.Presentation.ui.Screens.TodayScreen
import com.example.weatherproject.Presentation.ui.theme.WeatherProjectTheme
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(WeatherRepo(OpenMeteoWeather(), RoomWeatherDataSource()))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                checkGpsAndFetch()
            } else {
                Toast.makeText(this, "Location permission is required.", Toast.LENGTH_SHORT).show()
                requestLocationPermission()
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (isNetworkAvailable()) {
            checkGpsAndFetch()
        } else {
            Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show()
        }

        setContent {
            val navController = rememberNavController()
            WeatherProjectTheme {
                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        TodayScreen(navController, viewModel)
                    }

                    composable("detail/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                        DetailsScreen(navController, viewModel, index)
                    }

                    composable("fav") {
                        FavWeatherScreen(navController, viewModel)
                    }
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkGpsAndFetch() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            2000L
        ).build()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        LocationServices.getSettingsClient(this)
            .checkLocationSettings(builder.build())
            .addOnSuccessListener {
                requestLocationPermissionIfNeeded()
            }
            .addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    e.startResolutionForResult(this, 100)
                } else {
                    Toast.makeText(this, "GPS must be enabled.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestLocationPermissionIfNeeded() {
        val granted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        if (!granted) {
            requestLocationPermission()
        } else {
            fetchLocation()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    viewModel.getWeather(location.latitude, location.longitude)
                } else {
                    Toast.makeText(
                        this,
                        "GPS error — unable to fetch location.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val net = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(net) ?: return false
        return caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}
