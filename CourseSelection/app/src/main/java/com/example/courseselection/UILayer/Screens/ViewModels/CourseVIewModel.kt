package com.example.courseselection.UILayer.Screens.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.courseselection.DataLayer.Course
import com.example.courseselection.DataLayer.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.minus
import kotlin.collections.plus

class CourseVIewModel(
    private val courseRepo: CourseRepository = CourseRepository()
) : ViewModel(){
    private val _favoriteIds = MutableStateFlow<Set<Int>>(courseRepo.getAllFavIds())
    val favoriteIds : StateFlow<Set<Int>> = _favoriteIds.asStateFlow()

    private val _courses =  MutableStateFlow<List<Course>>(courseRepo.getAllCourses())
    val couses : StateFlow<List<Course>> = _courses.asStateFlow()

    fun updateFavIDs(id: Int){
        // Updating the repository
        courseRepo.updateIDs(id)
        // updating the UI state
        Log.d("Set In VM", _favoriteIds.value.toString())

        _favoriteIds.update { currentSet ->
            if (id in currentSet){
                currentSet - id
            }
            else {
                currentSet + id
            }
        }
        Log.d("Set In VM", _favoriteIds.value.toString())


    }


}