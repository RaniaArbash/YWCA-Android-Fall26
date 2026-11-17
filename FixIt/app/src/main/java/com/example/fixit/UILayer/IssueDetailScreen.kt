package com.example.fixit.UILayer

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
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
import androidx.core.app.ActivityCompat.startActivityForResult

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
               // cameraLauncher.launch(null)
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                   // putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(locationForPhotos, targetFilename))
                }
                try{
                    context.startActivity(intent)
                }
                catch (e: Exception){
                    Toast.makeText(context,"No such App", Toast.LENGTH_LONG).show()

                }
                //    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

            }){
                Text("Take a photo")
            }
//            Image(
//                bitmap = photoBitmap!!.asImageBitmap(),
//                contentDescription = "Temporary Photo Preview",
//                modifier = Modifier.fillMaxSize()
//            )


        }



    }


}
