package com.example.taskplanner.Screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplanner.Composables.TaskCard
import com.example.taskplanner.Composables.summaryCard
import com.example.taskplanner.Task
import com.example.taskplanner.ViewModels.TaskViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

// MVVM
@Composable
fun StudentTaskPlanner(viewModel: TaskViewModel ,
                       addNewTaskClicked : () -> Unit) {
    //val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val list by viewModel.tasksList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Student Planner", fontSize = 28.sp, color = Color.Gray)
        Text(text = "Welcome Back")
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            summaryCard(modifier = Modifier.weight(1f), title = "Total", value =list.size.toString())
            summaryCard(modifier = Modifier.weight(1f),
                title = "Completed",
                value =list.count{ task-> task.isCompleted }.toString() )
            summaryCard(modifier = Modifier.weight(1f),
                title = "Pending",
                value =list.count{!it.isCompleted}.toString())
        }
        ElevatedButton(
            onClick = {
                addNewTaskClicked()
                //navController.navigate("addTask")
            }) {
            Text("Add New Task")
        }

        LazyColumn{
            items(list){task ->
                TaskCard(task, onDelete = {
                    viewModel.deleteTask(task)
                })
            }
        }
    }
}



