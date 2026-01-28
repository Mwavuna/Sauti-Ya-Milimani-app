package com.example.sautiyamilimani_test1.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.features.onboarding.presentation.OnboardingScreen
import com.example.sautiyamilimani_test1.presentation.pages.admins.AttendanceManagement
import com.example.sautiyamilimani_test1.presentation.pages.admins.CardCreator
import com.example.sautiyamilimani_test1.presentation.pages.admins.EventsManagement
import com.example.sautiyamilimani_test1.presentation.pages.auth.AuthPage
import com.example.sautiyamilimani_test1.presentation.pages.testCocepts.HomePage
import com.example.sautiyamilimani_test1.presentation.pages.testCocepts.SignUpPage
import com.example.sautiyamilimani_test1.presentation.pages.admins.LeadersPage
import com.example.sautiyamilimani_test1.presentation.pages.admins.MemberManagementPage
import com.example.sautiyamilimani_test1.presentation.pages.admins.ProjectsManagement
import com.example.sautiyamilimani_test1.presentation.pages.admins.SongsManagement
import com.example.sautiyamilimani_test1.presentation.pages.members.MembersPage
import com.example.sautiyamilimani_test1.presentation.pages.testCocepts.LoginPage
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import com.example.sautiyamilimani_test1.presentation.viewmodels.MembersViewModel
import com.example.sautiyamilimani_test1.presentation.viewmodels.UiEvent
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    membersViewModel: MembersViewModel,
    navController: NavHostController
) {

    val currentUser by authViewModel.currentUser.collectAsState()
    val isLoading by authViewModel.isLoading.collectAsState()
    val isAdmin by authViewModel.isAdmin.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect(Unit) {
        authViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateEvent -> {
                    navController.navigate(event.route) {
                        popUpTo(event.route) { inclusive = true }
                    }
                }

                is UiEvent.SnackBarEvent -> {
                    snackbarHostState.showSnackbar("${event.message}")

                }
            }

        }

    }


    /*
         val currentUser by authViewModel.currentUser.collectAsState()
         is Kotlin’s shorthand way of saying:

         👉 “Define currentUser as the current value coming from authViewModel.currentUser.collectAsState().”

         Without by, you’d have to:

         val currentUserState = authViewModel.currentUser.collectAsState()
         val currentUser = currentUserState.value
     */

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                NavHost(
                    navController = navController,
                    startDestination =
                        Screen.Onboarding,
//                        if (currentUser == null) {
//                            Screen.Login
//                        } else if (currentUser?.isAnonymous ?: true) Screen.Member
//                        else {
//                            if (isAdmin) {
//                                Screen.Leader
//                            } else {
//                                Screen.Member
//                            }
//                        },
                    builder = {
                        composable<Screen.Login> {
                            AuthPage(authViewModel, navController)
                        }
                        composable<Screen.Login> {
                            LoginPage(
                                modifier = modifier,
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }
                        composable<Screen. Onboarding> {
                            OnboardingScreen(modifier, navController, authViewModel)
                        }
                        composable<Screen.Home> {
                            HomePage(modifier, navController, authViewModel)
                        }
                        composable< Screen.Member> {
                            MembersPage(modifier, navController, authViewModel)
                        }
                        composable<Screen.Leader> {
                            LeadersPage(modifier, navController, membersViewModel, authViewModel)
                        }
                        composable<Screen.MembersManagement> {
                            MemberManagementPage(navController, membersViewModel)
                        }
//                        composable<Screen.CardCreator> {
//                            CardCreator(navController)
//                        }
                        composable< Screen.Events>{
                            EventsManagement(modifier, navController)
                        }
                        composable< Screen.Songs> {
                            SongsManagement(modifier, navController)
                        }
                        composable<Screen.Projects> {
                            ProjectsManagement(modifier, navController)
                        }
                        composable< Screen.Attendance> {
                            AttendanceManagement(modifier, navController, membersViewModel)
                        }
                        composable<Screen.Minutes> {
                            AttendanceManagement(modifier, navController, membersViewModel)
                        }

                    })
            }
        }
    }
}
