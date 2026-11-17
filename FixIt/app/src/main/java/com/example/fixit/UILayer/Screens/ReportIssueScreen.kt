package com.example.fixit.UILayer.Screens


import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportIssueScreen(
    onNavigateBack: () -> Unit,
    onIssueSubmitted: () -> Unit,
    onSubmitIssue: (title: String, category: String, description: String) -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }

    var expanded by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val categories = listOf(
        "Computer Hardware",
        "Networking",
        "Software Support",
        "Mobile Devices",
        "Other"
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            photoBitmap = bitmap
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report Technical Issue") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Issue Title *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && title.isBlank()
            )

            // Category Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Issue Category *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    isError = showError && category.isBlank()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                category = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Issue Description *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                maxLines = 6,
                isError = showError && description.isBlank()
            )

            // Photo section
            OutlinedButton(
                onClick = { cameraLauncher.launch(null) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.CameraAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Take a Photo")
            }

            if (photoBitmap != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            bitmap = photoBitmap!!.asImageBitmap(),
                            contentDescription = "Temporary Photo Preview",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            if (showError && (title.isBlank() || category.isBlank() || description.isBlank())) {
                Text(
                    text = "Please fill in all required fields (*).",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (title.isBlank() || category.isBlank() || description.isBlank()) {
                        showError = true
                    } else {
                        onSubmitIssue(title, category, description)
                        onIssueSubmitted()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Submit Issue", fontSize = 16.sp)
            }
        }
    }
}
