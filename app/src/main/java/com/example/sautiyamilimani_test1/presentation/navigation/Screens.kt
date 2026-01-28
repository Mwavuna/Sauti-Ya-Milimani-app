package com.example.sautiyamilimani_test1.presentation.navigation

import com.example.sautiyamilimani_test1.domain.model.Event
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Splash:Screen()

   // @Serializable
    //data class Auth(val status:AuthStatus):Screen()

    @Serializable
    object Login:Screen()

    @Serializable
    object   Onboarding:Screen()

    @Serializable
    object SignUp:Screen()

    @Serializable
    data object Home:Screen()

    @Serializable
    data object Member:Screen()

    @Serializable
    data object MembersManagement:Screen()

    @Serializable
    data object Leader :Screen()

   // @Serializable
    //data class CardCreator(val event: Event) :Screen()

    @Serializable
    data object Events:Screen()

    @Serializable
    data object Minutes :Screen()

    @Serializable
    data object Songs:Screen()

    @Serializable
    data object Projects :Screen()

    @Serializable
    data object Attendance :Screen()

}