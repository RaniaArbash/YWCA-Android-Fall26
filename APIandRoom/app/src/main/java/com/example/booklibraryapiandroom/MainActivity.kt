package com.example.booklibraryapiandroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booklibraryapiandroom.data.local.BookDatabase
import com.example.booklibraryapiandroom.data.remote.RetrofitInstance
import com.example.booklibraryapiandroom.data.repository.BookRepository
import com.example.booklibraryapiandroom.ui.screens.FavoritesScreen
import com.example.booklibraryapiandroom.ui.screens.SearchScreen
import com.example.booklibraryapiandroom.ui.theme.BookLibraryAPIAndRoomTheme
import com.example.booklibraryapiandroom.ui.viewmodel.BookViewModel
import com.example.booklibraryapiandroom.ui.viewmodel.BookViewModelFactory

/**
 * Main Activity hosting the Jetpack Compose UI and Navigation for Book Explorer.
 */
class MainActivity : ComponentActivity() {
    private val bookViewModel: BookViewModel by viewModels {
        val database = BookDatabase.getDatabase(applicationContext)
        val repository = BookRepository(RetrofitInstance.api, database.bookDao())
        BookViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookLibraryAPIAndRoomTheme {
                val navController = rememberNavController()
                val items = listOf(
                    BottomNavItem("search", "Search", Icons.Default.Search),
                    BottomNavItem("favorites", "Favorites", Icons.Default.Favorite)
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            items.forEach { item ->
                                NavigationBarItem(
                                    icon = { Icon(item.icon, contentDescription = item.label) },
                                    label = { Text(item.label) },
                                    selected = currentRoute == item.route,
                                    onClick = {
                                        if (currentRoute != item.route) {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "search",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("search") {
                            SearchScreen(viewModel = bookViewModel)
                        }
                        composable("favorites") {
                            FavoritesScreen(viewModel = bookViewModel)
                        }
                    }
                }
            }
        }
    }




}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

