package com.example.fixit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.fixit.UILayer.Screens.IssueDetailScreen
import com.example.fixit.UILayer.IssueViewModel
import com.example.fixit.UILayer.Navigation.FixItNavGraph
import com.example.fixit.ui.theme.FixItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val issueViewModel : IssueViewModel = viewModel()

            FixItTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FixItNavGraph(issueViewModel)
                }
            }
        }
    }
}
