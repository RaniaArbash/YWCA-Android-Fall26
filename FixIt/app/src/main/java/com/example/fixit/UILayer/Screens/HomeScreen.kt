package com.example.fixit.UILayer.Screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateReportIssue: () -> Unit,
    onNavigateSpecialists: () -> Unit,
    onNavigateIssueHistory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FixIt Support Hub") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "FixIt",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Report technical problems and find the right support.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onNavigateReportIssue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Report an Issue", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onNavigateSpecialists,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(imageVector = Icons.Default.PersonSearch, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Find a Specialist", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onNavigateIssueHistory,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "My Reported Issues", fontSize = 16.sp)
            }
        }
    }
}
