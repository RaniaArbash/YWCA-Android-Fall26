package com.example.courseselection.UILayer.Screens.Composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.w3c.dom.Text

@Composable
fun MyButtomBar(onNavigate: (String)->Unit){
    var selectedIndex = remember { mutableStateOf(0) }
    NavigationBar() {
        NavigationBarItem(onClick = {
            onNavigate("home")
          //  selectedIndex.value = 1
        },
            selected = true,
            label = {
                Text("Home")
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
            )
        NavigationBarItem(onClick = {
            onNavigate("selectedCourse")
        },
            selected = true,
            label = { Text("Favorite")},
            icon = { Icon(Icons.Default.Star, contentDescription = "Fav") }
        )
    }
}