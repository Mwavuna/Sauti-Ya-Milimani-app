package com.example.sautiyamilimani.presentation.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.sautiyamilimani.presentation.viewmodels.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomePage(modifier: Modifier =Modifier,navController: NavController,authViewModel: AuthViewModel){

    val currentUser by authViewModel.currentUser.collectAsState()
    Row(
        modifier=Modifier.padding(horizontal = 16.dp, vertical = 48.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
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