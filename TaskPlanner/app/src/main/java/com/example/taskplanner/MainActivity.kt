package com.example.taskplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplanner.Screens.AddNewTaskScreen
import com.example.taskplanner.Screens.StudentTaskPlanner
import com.example.taskplanner.ui.theme.TaskPlannerTheme
// MVVM
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var sampleTasks = mutableListOf(
            Task(
                1,
                "Kotlin Assignment",
                "Complete exercises 1-5",
                "Nov 10",
                "University",
                false
            ),
            Task(
                2,
                "Study Compose",
                "Review Compose layouts",
                "Nov 11",
                "Study",
                true
            ),
            Task(
                3,
                "Prepare Presentation",
                "Prepare slides for class",
                "Nov 12",
                "University",
                false
            ),
            Task(
                4,
                "Read Chapter 3",
                "Review the textbook",
                "Nov 13",
                "Study",
                false
            ),
            Task(
                5,
                "Submit Lab",
                "Upload the completed project",
                "Nov 14",
                "University",
                true
            )
        )
        enableEdgeToEdge()
        setContent {
            TaskPlannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var title by rememberSaveable {
                        mutableStateOf("")
                    }
                    var description by rememberSaveable {
                        mutableStateOf("")
                    }
                    val navController = rememberNavController()
                    NavHost(navController =navController,
                        startDestination =  "home"){

                        composable(route = "home"){
                            // StudentTaskPlanner(sampleTasks,navController)
                            StudentTaskPlanner(sampleTasks,
                                addNewTaskClicked = {
                                navController.navigate("addTask")
                            })
                        }
                        composable(route = "addTask"){

                            AddNewTaskScreen(
                                title,
                                description,
                                {
                                   title = it
                                },
                                onDescriptionChaged = {
                                    description = it
                                },
                                onSave = {
                                    val newTask =
                                        Task(id = (sampleTasks.maxOfOrNull { it.id } ?: 0) + 1,
                                        title = title,
                                        description = description,
                                        dueDate = "Nov 9, 2026",
                                        category = "University",
                                        isCompleted = false
                                        )
                                    title = ""
                                    description = ""
                                    sampleTasks.add(newTask)
                                    navController.popBackStack()

                                },
                                onCancel = {
                                    title = ""
                                    description = ""
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