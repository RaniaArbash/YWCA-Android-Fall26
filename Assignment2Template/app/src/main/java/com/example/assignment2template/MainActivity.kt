package com.example.assignment2template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.assignment2template.ui.screens.HomeScreen
import com.example.assignment2template.ui.theme.Assignment2TemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2TemplateTheme {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
