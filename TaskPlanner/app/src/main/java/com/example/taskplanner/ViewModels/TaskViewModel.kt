package com.example.taskplanner.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskplanner.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ViewModel = UI State == UI data != buiness Logic or App data
// list of task == app data I have to read/write it from a database
// Screen mode = light or dark == UI state

class TaskViewModel : ViewModel() {

    private var _tasksList = MutableStateFlow<List<Task>>(listOf(Task(
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
    ))
    val tasksList : StateFlow<List<Task>> = _tasksList.asStateFlow()

    fun addNewTask(newTask : Task){
        _tasksList.update { currentList ->
            currentList + newTask
        }
        Log.d("list",_tasksList.value.toString())

        }
    }
