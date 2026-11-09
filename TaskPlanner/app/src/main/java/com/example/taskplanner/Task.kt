package com.example.taskplanner

data class Task(
    val id: Int,
    val title: String,
    val description : String,
    val dueDate: String,
    val category: String,
    val isCompleted: Boolean
    )

// val = constant
// var = variable