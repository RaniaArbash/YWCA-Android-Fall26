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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.courseselection.Screens.HomeScreen
import com.example.courseselection.Screens.SelectedCoursesScreen
import com.example.courseselection.ui.theme.CourseSelectionTheme

class MainActivity : ComponentActivity() {



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        var courses =  listOf(
            Course(
                1, "CS 101",
                "Introduction to Programming",
                3, "Dr. Smith",
                "Monday / Wednesday",
                "10:00 - 11:30 AM",
                "Room 204"
            ),
            Course(
                2, "CS 205",
                "Data Structures",
                4, "Dr. Johnson",
                "Tuesday / Thursday",
                "11:00 AM - 12:30 PM",
                "Room 310"
            ),
            Course(
                3, "MATH 201",
                "Discrete Mathematics",
                3, "Dr. Brown",
                "Monday / Wednesday",
                "1:00 - 2:30 PM",
                "Room 105"
            ),
            Course(
                4, "CS 310",
                "Software Engineering",
                3, "Dr. Wilson",
                "Sunday / Tuesday",
                "9:00 - 10:30 AM",
                "Room 220"
            ),
            Course(
                5, "CS 320",
                "Mobile Application Development",
                4, "Dr. Davis",
                "Monday / Wednesday",
                "3:00 - 4:30 PM",
                "Lab 12"
            ),
            Course(
                6, "CS 350",
                "Database Systems",
                3, "Dr. Miller",
                "Tuesday / Thursday",
                "2:00 - 3:30 PM",
                "Room 115"
            ),
            Course(
                7, "CS 410",
                "Artificial Intelligence",
                3, "Dr. Taylor",
                "Sunday / Tuesday",
                "11:00 AM - 12:30 PM",
                "Room 305"
            ),
            Course(
                8, "CS 430",
                "Computer Networks",
                3, "Dr. Anderson",
                "Monday / Wednesday",
                "9:00 - 10:30 AM",
                "Room 210"
            )
        )
        setContent {
            CourseSelectionTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {Text(text = "Course Selection App")},
                            actions = {
                                IconButton(onClick = {
                                    navController.navigate("selectedCourse")
                                }) {
                                    Icon(imageVector = Icons.Default.Star,
                                        contentDescription = "navigate to another screen"
                                    )
                                }
                            })
                    }
                //, topBar = TopAppBar(title = {}, actions = {})
                ) { innerPadding ->

                    var favoriteIds by remember {
                        mutableStateOf(setOf<Int>())
                    }

                    NavHost(navController = navController,
                        startDestination = "home"
                        ){
                        composable(route = "home"){
                            HomeScreen(innerPadding,

                                courses,
                                favoriteIds,
                                favoritClicked = {
                                    selectedID ->
                                    Log.d("selected ID", selectedID.toString())

                                    favoriteIds =
                                        if (selectedID in favoriteIds){
                                        favoriteIds - selectedID
                                        }
                                    else {
                                            favoriteIds + selectedID
                                        }
                                    Log.d("listOfIDS", favoriteIds.toString())

                                })
                        }

                        composable(route="selectedCourse"){
                            SelectedCoursesScreen()
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