package com.example.sautiyamilimani_test1.presentation.navigation

sealed class Screen (val route:String){
    object Splash:Screen("splash")

    object Auth:Screen("Auth")
    object Login:Screen("login")
    object SignUp:Screen("signup")
    object Home:Screen("home")

    object Member:Screen("memberPage")

    object MembersManagement:Screen("membersManagement")

    object Leader :Screen("leadersPage")
    object CardCreator :Screen("cardCreator")
    object Events:Screen("events")
    object Minutes :Screen("minutes")
    object Songs:Screen("songs")
    object Projects :Screen("projects")
    object Attendance :Screen("attendance")

}