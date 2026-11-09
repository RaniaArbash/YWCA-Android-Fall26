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
import com.example.taskplanner.Composables.TaskCard
import com.example.taskplanner.Composables.summaryCard
import com.example.taskplanner.Task


@Composable
fun StudentTaskPlanner() {
    var sampleTasks = listOf(
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
    // higher order functions
    var totalCompletedTask =   sampleTasks.count { it.isCompleted}// $1
    var totalPendingTasks = sampleTasks.count{ t-> !t.isCompleted}
    sampleTasks.forEach { task ->
        Log.d("planner",task.title)
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Student Planner", fontSize = 28.sp, color = Color.Gray)
        Text(text = "Welcome Back")
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            summaryCard(modifier = Modifier.weight(1f), title = "Total", value = sampleTasks.size.toString())
            summaryCard(modifier = Modifier.weight(1f),
                title = "Completed",
                value = sampleTasks.count{ task-> task.isCompleted }.toString() )
            summaryCard(modifier = Modifier.weight(1f),
                title = "Pending",
                value = sampleTasks.count{!it.isCompleted}.toString())
        }
        ElevatedButton(
            onClick = {

            }) {
            Text("Add New Task")
        }

        LazyColumn{
            items(sampleTasks){task ->
                TaskCard(task)
            }
        }
    }
}



