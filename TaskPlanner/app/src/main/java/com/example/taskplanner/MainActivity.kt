package com.example.taskplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplanner.DataLayer.Task
import com.example.taskplanner.UILayer.ViewModels.Composables.MyFAB
import com.example.taskplanner.UILayer.ViewModels.Screens.AddNewTaskScreen
import com.example.taskplanner.UILayer.ViewModels.Screens.StudentTaskPlanner
import com.example.taskplanner.UILayer.ViewModels.TaskViewModel
import com.example.taskplanner.ui.theme.TaskPlannerTheme
// MVVM == ViewModel where all UI State are stored and managed
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskPlannerTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        MyFAB(onClick = {
                            navController.navigate("addTask")

                        })
                }) { innerPadding ->
                    val taskViewModel: TaskViewModel = viewModel()

                    NavHost(navController =navController,
                        startDestination =  "home"){
                        composable(route = "home"){
                            StudentTaskPlanner(taskViewModel)
                        }
                        composable(route = "addTask"){
                            AddNewTaskScreen(taskViewModel,
                                onSave = {
                                    navController.popBackStack()
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
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
    TaskPlannerTheme {
        //StudentTaskPlanner()
    }
}