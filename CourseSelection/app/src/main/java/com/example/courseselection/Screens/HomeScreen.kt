package com.example.courseselection.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseselection.Composables.CourseCard
import com.example.courseselection.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    courseList : List<Course>,
               favList: Set<Int>,
               favoritClicked: (id: Int)-> Unit,
                movetoFavClicked : () -> Unit
    ){
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(text = "Course Selection App")},
                actions = {
                    IconButton(onClick = {
                        movetoFavClicked()
                    }) {
                        Icon(imageVector = Icons.Default.Star,
                            contentDescription = "navigate to another screen"
                        )
                    }
                })
        }
        //, topBar = TopAppBar(title = {}, actions = {})
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize().padding(innerPadding)
        ) {

            Text(
                text = "Available Courses",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    vertical = 12.dp
                )
            )

            LazyColumn(
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = courseList,
                    key = { it.id })
                { course ->
                    CourseCard(
                        course = course,
                        course.id in favList,
                        favoritClicked = { favoritClicked(it) }
                    )
                }
            }
        }
    }
}