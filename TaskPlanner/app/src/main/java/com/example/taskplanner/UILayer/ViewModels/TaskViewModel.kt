package com.example.taskplanner.UILayer.ViewModels

import androidx.lifecycle.ViewModel
import com.example.taskplanner.DataLayer.TasksRepository
import com.example.taskplanner.DataLayer.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ViewModel = UI State == UI data != buiness Logic or App data
// list of task == app data I have to read/write it from a database
// Screen mode = light or dark == UI state

class TaskViewModel(
    private val tasksRepo: TasksRepository = TasksRepository())
    : ViewModel() {

    private var _tasksList = MutableStateFlow<List<Task>>(tasksRepo.getAllTask())
    val tasksList : StateFlow<List<Task>> = _tasksList.asStateFlow()

    fun addNewTask(newTask : Task){
        _tasksList.update { currentList ->
            currentList + newTask // Update UI Layer
        }
        tasksRepo.addOneTask(newTask)// Update the Data Layer

    }


    fun deleteTask(taskToDelete : Task){
        _tasksList.update { currentList ->
            currentList - taskToDelete
        }
         tasksRepo.deleteOneTask(taskToDelete)

    }
    }
