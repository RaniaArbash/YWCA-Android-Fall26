package com.example.taskplanner.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskplanner.Task


@Composable
fun TaskCard(task: Task){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = task.description,
                    fontSize = 14.sp
                )

                Text(
                    text = "Due: ${task.dueDate}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = if (task.isCompleted) "Completed" else "Pending",
                color = if (task.isCompleted) Color(0xFF2E7D32)
                else Color(0xFFE65100),
                fontWeight = FontWeight.Bold
            )
        }
    }
}