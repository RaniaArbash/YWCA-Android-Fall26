package com.example.fixit.UILayer

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.fixit.DataLayer.Issue
import com.example.fixit.DataLayer.Specialist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialistProfileScreen(
    specialist: Specialist?,
    currentIssue: Issue?,
    onNavigateBack: ()->Unit
){
var context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Specialist Profile") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                       // Icon(Icons., contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ){innerPadding ->

        Row(modifier = Modifier.padding(innerPadding)){
            Button(onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${specialist?.phone}")
                }
                try {
                    context.startActivity(intent)
                }
                catch (e: Exception){
                   Toast.makeText(context,"No such App", Toast.LENGTH_LONG).show()
                }

            }) {
               Text("Call")
            }

            Button(onClick = {
                val subject = "Technical Support Request${currentIssue?.let { " - ${it.title}" } ?: ""}"
                val body = buildString {
                    append("Hello ${specialist?.name},\n\n")
                    append("I am reaching out regarding technical support.\n")
                    if (currentIssue != null) {
                        append("Issue Title: ${currentIssue.title}\n")
                        append("Category: ${currentIssue.category}\n")
                        append("Description:\n${currentIssue.description}\n\n")
                    }
                    append("Please let me know your availability.\n\nThank you!")
                }
                Log.d("title",subject)
                Log.d("body", body)
                val intent = Intent(Intent.ACTION_SENDTO).apply {

                     data = Uri.parse("mailto:${specialist?.email}")
                    putExtra(Intent.EXTRA_SUBJECT,subject)
                    putExtra(Intent.EXTRA_TEXT, body)

                }
                try {

                    context.startActivity(intent)
                }catch (e: Exception){
                    Toast.makeText(context,"No such App", Toast.LENGTH_LONG).show()
                }

            }) {
                Text("Email")
            }
        }
    }


}