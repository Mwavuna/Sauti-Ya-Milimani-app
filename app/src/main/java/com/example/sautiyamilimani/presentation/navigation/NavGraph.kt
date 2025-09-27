package com.example.sautiyamilimani.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sautiyamilimani.presentation.pages.HomePage
import com.example.sautiyamilimani.presentation.pages.LoginPage
import com.example.sautiyamilimani.presentation.pages.SignUpPage
import com.example.sautiyamilimani.presentation.viewmodels.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val currentUser by authViewModel.currentUser.collectAsState()

   /*
        val currentUser by authViewModel.currentUser.collectAsState()
        is Kotlin’s shorthand way of saying:

        👉 “Define currentUser as the current value coming from authViewModel.currentUser.collectAsState().”

        Without by, you’d have to:

        val currentUserState = authViewModel.currentUser.collectAsState()
        val currentUser = currentUserState.value
    */

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) Screen.Home.route else Screen.Login.route,
        builder = {
            composable(route = Screen.Login.route) {
                LoginPage(modifier, navController, authViewModel)
            }
            composable(route = Screen.SignUp.route) {
                SignUpPage(modifier, navController, authViewModel)
            }
            composable(route = Screen.Home.route) {
                HomePage(modifier, navController, authViewModel)
            }

        })
}