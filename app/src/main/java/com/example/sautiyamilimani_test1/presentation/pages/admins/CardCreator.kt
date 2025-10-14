package com.example.sautiyamilimani_test1.presentation.pages.admins

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.presentation.components.FundraisingCard
import kotlin.apply


sealed class ImageState {
    data class SAVED_SUCCESSFULLY(val message: String) : ImageState()
    data class ERROR(val message: String) : ImageState()
}

@Composable
fun CardCreator(navController: NavController) {
    var amount by rememberSaveable {
        mutableIntStateOf(500)
    }
    var filename by rememberSaveable {
        mutableStateOf("Test")
    }
    var bitmap: Bitmap? by remember {
        mutableStateOf(null)
    }

    var cardViewRef by remember { mutableStateOf<ComposeView?>(null) }


    val context = LocalContext.current

    val snackBarHostState = remember { SnackbarHostState() }


    var imageState by remember { mutableStateOf<ImageState?>(null) }

    LaunchedEffect(imageState) {
        when (imageState) {
            is ImageState.ERROR -> snackBarHostState.showSnackbar((imageState as ImageState.ERROR).message)
            is ImageState.SAVED_SUCCESSFULLY -> snackBarHostState.showSnackbar((imageState as ImageState.SAVED_SUCCESSFULLY).message)
            null -> {}
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }, topBar = {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }, modifier = Modifier.weight(.1f)) {
                Icon(Icons.Filled.ArrowBack, null)
            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                Text("Card Generator")
            }

        }
    }

    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.Center
            ) {

            }



            AndroidView(
                factory = { context ->
                    ComposeView(context).apply {
                        setContent {
                            FundraisingCard(amount)
                        }
                        cardViewRef = this
                    }

                }, modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    shape = MaterialTheme.shapes.medium,
                    onClick = {

                        try {
                            bitmap = createBitmapFromComposable(
                                cardViewRef
                            )

                            imageState =
                                ImageState.SAVED_SUCCESSFULLY("Card Generated Successfully!")

                        } catch (e: Exception) {
                            imageState = ImageState.ERROR("Card Generation Failed!")
                            Log.e("CardCreator", "Failed to save bitmap", e)
                        }

                    }) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Create, "Generate Card")
                        Text("Generate Card ")
                    }
                }
            }


            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {

                bitmap?.let {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Image(bitmap = it.asImageBitmap(), "Event Card")

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 24.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    try {
                                        saveToExternalStorage(
                                            context, bitmap, "Church_Card", onSaved = {
                                                imageState = ImageState.SAVED_SUCCESSFULLY(it)
                                            })
                                    } catch (e: Exception) {
                                        imageState = ImageState.ERROR("Failed to Save!")
                                        Log.d("External Storage", "Failed to save", e)
                                    }
                                }, shape = MaterialTheme.shapes.medium
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(Icons.Default.Download, "Download Card")
                                    Text("Download Card")
                                }
                            }
                        }
                    }

                } ?: Text("No Card Yet")


            }


        }


    }
}

fun createBitmapFromComposable(cardViewRef: ComposeView?): Bitmap? {
    return cardViewRef?.drawToBitmap()

}


fun saveToExternalStorage(
    context: Context, bitmap: Bitmap?, filename: String, onSaved: (uri: String) -> Unit
) {

    val relativePath = "Pictures/SautiYaMilimani"
    if (bitmap == null) return
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$filename.png")
        put(MediaStore.Images.Media.MIME_TYPE, "images/png")
        put(MediaStore.Images.Media.RELATIVE_PATH, relativePath)
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val uri = context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
    )

    uri?.let {
        context.contentResolver.openOutputStream(it)?.use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        values.clear()
        values.put(MediaStore.Images.Media.IS_PENDING, 0)
        context.contentResolver.update(it, values, null, null)
        onSaved("Image Saved at $relativePath as $filename")

    }


}
