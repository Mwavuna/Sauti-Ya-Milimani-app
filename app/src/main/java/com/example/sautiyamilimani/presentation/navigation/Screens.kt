package com.example.sautiyamilimani.presentation.navigation

sealed class Screen (val route:String){
    object Splash:Screen("splash")
    object Login:Screen("login")
    object SignUp:Screen("signup")
    object Home:Screen("home")
}