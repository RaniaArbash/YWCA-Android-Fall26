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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplanner.Composables.TaskCard
import com.example.taskplanner.Composables.summaryCard
import com.example.taskplanner.Task

// MVVM
@Composable
fun StudentTaskPlanner(tasks : List<Task>,
                       //navController: NavController){
                       addNewTaskClicked : () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Student Planner", fontSize = 28.sp, color = Color.Gray)
        Text(text = "Welcome Back")
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            sum maryCard(modifier = Modifier.weight(1f), title = "Total", value = tasks.size.toString())
            summaryCard(modifier = Modifier.weight(1f),
                title = "Completed",
                value = tasks.count{ task-> task.isCompleted }.toString() )
            summaryCard(modifier = Modifier.weight(1f),
                title = "Pending",
                value = tasks.count{!it.isCompleted}.toString())
        }
        ElevatedButton(
            onClick = {
                addNewTaskClicked()
                //navController.navigate("addTask")
            }) {
            Text("Add New Task")
        }

        LazyColumn{
            items(tasks){task ->
                TaskCard(task)
            }
        }
    }
}



