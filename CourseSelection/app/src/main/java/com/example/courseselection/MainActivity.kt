package com.example.courseselection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courseselection.UILayer.Screens.Composables.MyButtomBar
import com.example.courseselection.UILayer.Screens.HomeScreen
import com.example.courseselection.UILayer.Screens.SelectedCoursesScreen
import com.example.courseselection.UILayer.Screens.ViewModels.CourseVIewModel
import com.example.courseselection.ui.theme.CourseSelectionTheme

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseSelectionTheme {
                val navController = rememberNavController()
                var viewModel: CourseVIewModel = viewModel()

                val courseList by viewModel.couses.collectAsStateWithLifecycle()
                val favList by viewModel.favoriteIds.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MyButtomBar(onNavigate = { path ->
                            navController.navigate(path)

                        })
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(route = "home") {
                            HomeScreen(
                                innerPadding,
                                viewModel,
                                favoritClicked = { selectedID ->
                                    viewModel.updateFavIDs(selectedID)
                                }
                            )
                        }

                        composable(route = "selectedCourse") {
                            val favoriteCourses = courseList.filter {
                                it.id in favList
                            }
                            SelectedCoursesScreen(
                                innerPadding,
                                courseList = favoriteCourses
                            )
                        }
                    }

                }
            }
        }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CourseSelectionTheme {
      //  Greeting("Android")
    }
}