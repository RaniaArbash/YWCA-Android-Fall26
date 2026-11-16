package com.example.taskplanner.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskplanner.Task
import com.example.taskplanner.ViewModels.TaskViewModel
import com.example.taskplanner.ui.theme.TaskPlannerTheme


@Composable
fun AddNewTaskScreen(viewModel: TaskViewModel ,
                     onSave: ()-> Unit,
                     onCancel : () -> Unit
                     ){
    val list by viewModel.tasksList.collectAsStateWithLifecycle()

    var title by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Task Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it},
            label = { Text(text = "Task Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Row() {
            ElevatedButton(onClick = {
                viewModel.addNewTask(
                    Task(id = (list.maxOfOrNull { it.id } ?: 0) + 1,
                    title = title,
                    description = description,
                    dueDate = "Nov 16, 2026",
                    category = "University",
                    isCompleted = false
                ))
                title = ""
                description = ""
                onSave()
            }) {
                Text(text = "Save")
            }
            ElevatedButton(onClick = {
                onCancel()

            }) {
                Text(text = "Cancel")
            }
        }

    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskPlannerTheme {
       // AddNewTaskScreen()
    }
}