package com.example.courseselection

data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val credits: Int,
    val instructor: String,
    val days: String,
    val time: String,
    val room: String
)