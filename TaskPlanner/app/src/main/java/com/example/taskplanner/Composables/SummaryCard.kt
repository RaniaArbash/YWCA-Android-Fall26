package com.example.taskplanner.Composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun summaryCard(modifier: Modifier, title: String, value: String){
    // title = "Total"/"Completed"/"Pending"
    // number // the number of each task group
    Card(modifier = modifier.fillMaxWidth(0.4f)){
        Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title)
            Text(text = value)
        }
    }
}