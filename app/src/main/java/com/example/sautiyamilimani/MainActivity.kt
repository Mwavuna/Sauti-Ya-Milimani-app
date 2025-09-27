package com.example.sautiyamilimani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sautiyamilimani.presentation.navigation.MyAppNavigation
import com.example.sautiyamilimani.presentation.viewmodels.AuthViewModel
import com.example.sautiyamilimani.ui.theme.SautiYaMilimaniTheme

class MainActivity : ComponentActivity() {
    private val authViewmodel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
           setContent {
            SautiYaMilimaniTheme {

                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        navController=navController,
                        authViewModel = authViewmodel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


