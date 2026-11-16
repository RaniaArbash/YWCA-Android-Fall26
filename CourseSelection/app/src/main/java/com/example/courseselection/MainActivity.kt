package com.example.courseselection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courseselection.Screens.HomeScreen
import com.example.courseselection.Screens.SelectedCoursesScreen
import com.example.courseselection.ViewModels.CourseVIewModel
import com.example.courseselection.ui.theme.CourseSelectionTheme
import kotlinx.coroutines.flow.filter

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseSelectionTheme {
                val navController = rememberNavController()
                    var viewModel : CourseVIewModel = viewModel()

                    val courseList by viewModel.couses.collectAsStateWithLifecycle()
                     val favList by viewModel.favoriteIds.collectAsStateWithLifecycle()

                NavHost(navController = navController,
                        startDestination = "home"
                        ){
                        composable(route = "home"){
                            HomeScreen(
                                viewModel,
                                favoritClicked = {
                                    selectedID ->
                                    viewModel.updateFavIDs(selectedID)
                                },
                                movetoFavClicked = {
                                     navController.navigate("selectedCourse")
                                }
                                )
                        }

                        composable(route="selectedCourse"){
                            val favoriteCourses = courseList.filter {
                                it.id in favList
                            }
                            SelectedCoursesScreen(
                                courseList = favoriteCourses,
                                backButtonCLicked = {
                                    navController.popBackStack()
                                }
                                )
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