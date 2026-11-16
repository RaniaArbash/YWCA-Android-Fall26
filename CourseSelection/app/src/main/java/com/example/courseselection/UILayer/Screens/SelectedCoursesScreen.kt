package com.example.courseselection.UILayer.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courseselection.UILayer.Screens.Composables.CourseCard
import com.example.courseselection.DataLayer.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCoursesScreen(
    padding: PaddingValues,
    courseList : List<Course>
){
        if (courseList.isEmpty()){
            Box(// ZStack
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "You haven't added any favorite courses yet.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }
        else {

            LazyColumn(
                modifier = Modifier.padding(padding),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)) {
                items(
                    items = courseList,
                    key = {it.id})
                { c -> // there is a for loop here
                    CourseCard(
                        course = c,
                        true,
                        favoritClicked = { }
                    )
                }
            }

        }

    }