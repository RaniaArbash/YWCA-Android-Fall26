package com.example.fixit.UILayer.Screens

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun IssueDetailScreen(){
    var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            photoBitmap = bitmap
        }
    }
    Scaffold(){
        innerPadding->

        Column(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = {
                cameraLauncher.launch(null)
            }){
                Text("Take a photo")
            }
            photoBitmap?.asImageBitmap()?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Temporary Photo Preview",
                    modifier = Modifier.fillMaxSize()
                )
            }


        }



    }


}
