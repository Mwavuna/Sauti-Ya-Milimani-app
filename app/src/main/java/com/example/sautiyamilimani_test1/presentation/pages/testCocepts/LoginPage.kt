package com.example.sautiyamilimani_test1.presentation.pages.testCocepts

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.presentation.navigation.Screen
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch

@Composable
fun LoginPage(modifier: Modifier =Modifier,navController: NavController,authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val currentUser by authViewModel.currentUser.collectAsState()

    //To obtain a coroutine scope we can use to run our login suspend fun in
    val scope = rememberCoroutineScope()

    //Observe any changes to the login state so that we can react accordingly
    val loginState by authViewModel.loginState.collectAsState()

    /*How to react when login state changes to success
        We will know if its successful if the type is Resource.success
        Then we will navigate to the home screen and remove screen between
        the home screen and the login screen in tha backstack entry to avoid going back to login screen when one click back
     */

    Box(modifier = Modifier.fillMaxSize()) {
        LaunchedEffect(loginState) {
            if (loginState is Resource.Success && currentUser!=null) {
                navController.navigate(Screen.Home) {
                    popUpTo(Screen.Login) { inclusive = true }
                }
            }
        }



        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", fontSize = 32.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        authViewModel.loginWithEmail(email, password)
                    }

                }
            ) {
                Text("Login")
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalDivider(Modifier.weight(1f))
                Text("OR", modifier = Modifier.padding(horizontal = 8.dp))
                HorizontalDivider(Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = {
                scope.launch {
                    signInWithGoogle(context, navController, authViewModel)
                }
            }) {
                Text("Continue with Google")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Don't have an account?")
                TextButton(
                    onClick = {
                        navController.navigate(Screen.SignUp)
                    },
                ) {
                    Text("SignUp")
                }
            }

        }
    }
    }

    private suspend fun signInWithGoogle(
        context: Context,
        navController: NavController,
        viewModel: AuthViewModel
    ) {


        try {
            //call function to get idToken from google
            val idToken = getIdToken(context)

            //give the viewmodel the token id to submit to firebase for auth
            viewModel.loginWithGoogle(idToken)


        } catch (e: GetCredentialException) {
            Log.d("Credetials not found", "${e.message}")
        }
    }


    //Function to obtain the IdToken from google
    private suspend fun getIdToken(context: Context): String {

        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption
            .Builder()
            .setServerClientId("977358680261-n3qf4tav9sfovkhagp3i9ef3s5gde3ih.apps.googleusercontent.com")
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val result = credentialManager.getCredential(context, request)
        val googleIdTokenCredential = result.credential as GoogleIdTokenCredential
        val idToken = googleIdTokenCredential.idToken
        return idToken
    }

