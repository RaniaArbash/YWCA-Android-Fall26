package com.example.courseselection.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.courseselection.Course
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.minus
import kotlin.collections.plus

class CourseVIewModel : ViewModel(){
    private val _favoriteIds = MutableStateFlow<Set<Int>>(setOf())
    val favoriteIds : StateFlow<Set<Int>> = _favoriteIds.asStateFlow()

    private val _courses =  MutableStateFlow<List<Course>>(listOf(
        Course(
            1, "CS 101",
            "Introduction to Programming",
            3, "Dr. Smith",
            "Monday / Wednesday",
            "10:00 - 11:30 AM",
            "Room 204"
        ),
        Course(
            2, "CS 205",
            "Data Structures",
            4, "Dr. Johnson",
            "Tuesday / Thursday",
            "11:00 AM - 12:30 PM",
            "Room 310"
        ),
        Course(
            3, "MATH 201",
            "Discrete Mathematics",
            3, "Dr. Brown",
            "Monday / Wednesday",
            "1:00 - 2:30 PM",
            "Room 105"
        ),
        Course(
            4, "CS 310",
            "Software Engineering",
            3, "Dr. Wilson",
            "Sunday / Tuesday",
            "9:00 - 10:30 AM",
            "Room 220"
        ),
        Course(
            5, "CS 320",
            "Mobile Application Development",
            4, "Dr. Davis",
            "Monday / Wednesday",
            "3:00 - 4:30 PM",
            "Lab 12"
        ),
        Course(
            6, "CS 350",
            "Database Systems",
            3, "Dr. Miller",
            "Tuesday / Thursday",
            "2:00 - 3:30 PM",
            "Room 115"
        ),
        Course(
            7, "CS 410",
            "Artificial Intelligence",
            3, "Dr. Taylor",
            "Sunday / Tuesday",
            "11:00 AM - 12:30 PM",
            "Room 305"
        ),
        Course(
            8, "CS 430",
            "Computer Networks",
            3, "Dr. Anderson",
            "Monday / Wednesday",
            "9:00 - 10:30 AM",
            "Room 210"
        )
    ))
    val couses : StateFlow<List<Course>> = _courses.asStateFlow()

    fun updateFavIDs(id: Int){
        _favoriteIds.update { currentSet ->
            if (id in currentSet){
                currentSet - id
            }
            else {
                currentSet + id
            }
        }

    }


}