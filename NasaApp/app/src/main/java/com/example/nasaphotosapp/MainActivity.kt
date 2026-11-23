package com.example.nasaphotosapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nasaphotosapp.UILayer.NasaViewModel
import com.example.nasaphotosapp.UILayer.Screens.ImageDetailsScreen
import com.example.nasaphotosapp.ui.screens.NASAHomeScreen
import com.example.nasaphotosapp.ui.theme.NasaPhotosAppTheme
class MainActivity : ComponentActivity() {

    private val viewModel : NasaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NasaPhotosAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MarsExplorerApp(viewModel = viewModel)


                }
            }
        }
    }



    @Composable
    fun MarsExplorerApp(viewModel: NasaViewModel) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                NASAHomeScreen(
                    viewModel = viewModel,
                    onImageSelect = { nasaId ->
                        navController.navigate("details/$nasaId")
                    }
                )
            }
            composable(
                route = "details/{nasaId}",
                arguments = listOf(
                    navArgument("nasaId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val nasaId = backStackEntry.arguments?.getString("nasaId") ?: ""
                val image = viewModel.getImageById(nasaId)
                ImageDetailsScreen(
                    image = image,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }

}
