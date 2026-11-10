package com.example.courseselection.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseselection.Course

@Composable
fun CourseCard(course: Course,
               isFavorite: Boolean,
               favoritClicked : (id:Int)-> Unit
){

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Course code + Favorite star
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = course.code,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                IconButton(
                    onClick = {
                        favoritClicked(course.id)
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Default.Star
                        } else {
                            Icons.Outlined.Star
                        },
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) {
                            Color(0xFFFFB300)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }

            // Course name

            Text(
                text = course.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Course information

            CourseInfo(
                label = "Credits",
                value = "${course.credits} credits"
            )

            CourseInfo(
                label = "Instructor",
                value = course.instructor
            )

            CourseInfo(
                label = "Days",
                value = course.days
            )

            CourseInfo(
                label = "Time",
                value = course.time
            )

            CourseInfo(
                label = "Location",
                value = course.room
            )
        }
    }
}