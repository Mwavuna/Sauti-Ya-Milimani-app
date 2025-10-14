package com.example.sautiyamilimani_test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sautiyamilimani_test1.presentation.navigation.MyAppNavigation
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import com.example.sautiyamilimani_test1.presentation.viewmodels.MembersViewModel
import com.example.sautiyamilimani_test1.ui.theme.SautiYaMilimaniTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
           setContent {
            SautiYaMilimaniTheme {

                val navController = rememberNavController()
                val authViewModel: AuthViewModel = androidx.hilt.navigation.compose.hiltViewModel()
                val membersViewModel: MembersViewModel = androidx.hilt.navigation.compose.hiltViewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        navController=navController,
                        authViewModel = authViewModel,
                        membersViewModel= membersViewModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


