package com.example.courseselection.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseselection.Composables.CourseCard
import com.example.courseselection.Course

@Composable
fun HomeScreen(courseList : List<Course>,
               favoritClicked: (id: Int)-> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
            Arrangement.spacedBy(12.dp)) {

            items(
                items = courseList,
                key = {it.id})
            { course ->
                CourseCard(
                    course = course,
                    false,
                    favoritClicked = {favoritClicked(it)}
                    )
            }
        }
    }

}