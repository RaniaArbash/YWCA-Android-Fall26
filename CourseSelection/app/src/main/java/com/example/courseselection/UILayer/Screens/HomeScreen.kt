package com.example.courseselection.UILayer.Screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.courseselection.UILayer.Screens.Composables.CourseCard
import com.example.courseselection.UILayer.Screens.Composables.MyButtomBar
import com.example.courseselection.UILayer.Screens.ViewModels.CourseVIewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(padding: PaddingValues,
                viewModel: CourseVIewModel,
                favoritClicked: (id: Int)-> Unit
    ){

    val courseList by viewModel.couses.collectAsStateWithLifecycle()
    val favList by viewModel.favoriteIds.collectAsStateWithLifecycle()


        Column(
            modifier = Modifier
                .fillMaxSize().padding(padding)
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
                        favoritClicked = {
                            favoritClicked(it)
                        }
                    )
                }
            }
        }
    }
