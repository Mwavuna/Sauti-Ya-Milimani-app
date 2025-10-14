package com.example.sautiyamilimani_test1.presentation.pages.testCocepts


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun HomePage(modifier: Modifier =Modifier,navController: NavController,authViewModel: AuthViewModel){

    val currentUser by authViewModel.currentUser.collectAsState()
    val scope = rememberCoroutineScope()

    Log.d("Found User",currentUser?.displayName?:"null")

    Row(
        modifier=Modifier.padding(horizontal = 16.dp, vertical = 48.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){

        Row(modifier= Modifier.fillMaxWidth().weight(1f)){
            TextButton(onClick = {
                scope.launch {
                    authViewModel.logout()
                }

            }) {
                Text("Logout")
            }

        }
        val painter=rememberAsyncImagePainter(currentUser?.profilePic)
        Image(painter, contentDescription = "Profile Picture",
            modifier=Modifier
                .requiredSize(32.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(16.dp))
        Text(currentUser?.displayName?:"guest")
    }
}