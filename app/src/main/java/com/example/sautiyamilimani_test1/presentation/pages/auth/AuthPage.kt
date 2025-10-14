package com.example.sautiyamilimani_test1.presentation.pages.auth

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.presentation.components.FieldType
import com.example.sautiyamilimani_test1.presentation.components.GoogleSignInButton
import com.example.sautiyamilimani_test1.presentation.components.InputField
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import com.example.sautiyamilimani_test1.ui.theme.SautiYaMilimaniTheme
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.launch


@Composable
fun AuthPage(authViewModel: AuthViewModel, navController: NavController) {
    SautiYaMilimaniTheme {
        val context = LocalContext.current
        Page(authViewModel, navController, context)
    }
}

@Composable
fun Page(authViewModel: AuthViewModel, navController: NavController, context: Context) {
    val colors = MaterialTheme.colorScheme

    // Gradient Background
    val backgroundGradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(0.08f),
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        ),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    //Tabs list
    val tabs = listOf(
        TabItem("Member"),
        TabItem("Leader")
    )

    var isLoading by remember { mutableStateOf(false) }


    //Whole Screen in a column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        //Logo with shadow
        Box(
            modifier = Modifier
                .size(84.dp)
                .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                .background(colors.primary, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("S.Y.M", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))


        //App NAme
        Text(
            text = "Sauti Ya Milimani",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )


        // Subtitle
        Text(
            text = "Youth Management System",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary.copy(.5f),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))

        //Tabs heders display
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.primary.copy(0.05f),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, colors.primary.copy(0.5f), RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
        ) {
            tabs.forEachIndexed { index, tabItem ->

                //Each Tab
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .background(color = if (selectedTabIndex == index) MaterialTheme.colorScheme.surface else Color.Transparent),
                    text = {
                        Text(
                            tabItem.title,
                            color = if (selectedTabIndex == index)
                                colors.onSurface
                            else
                                colors.primary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    },
                )
            }
        }
        Spacer(Modifier.height(24.dp))

        //Tabs content to display with ref to card
        when (selectedTabIndex) {
            0 -> MemberTab(
                authViewModel, navController, context,
                onStartedLoading = {
                    isLoading = true
                }, onDoneLoading = {
                    isLoading = false
                })

            1 -> LeaderTab(
                navController, authViewModel, context,
                onStartedLoading = {
                    isLoading = true
                }, onDoneLoading = {
                    isLoading = false
                })
        }


    }


}

//Contents of members Auth Pages
@Composable
fun MemberTab(
    authViewModel: AuthViewModel,
    navController: NavController,
    context: Context,
    onStartedLoading: () -> Unit,
    onDoneLoading: () -> Unit
) {

    val signUpState by authViewModel.signUpState.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatedPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isRegistering by rememberSaveable {
        mutableStateOf(true)
    }


    val scope = rememberCoroutineScope()


    LaunchedEffect(currentUser) {
        isRegistering = currentUser != null
    }


    //Container
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .verticalScroll(rememberScrollState()) // scroll when content is tall
            .imePadding(),  // push up when keyboard appears,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(24.dp),
    ) {
        //Column to hold all content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {

            //Title of Form depending on login or register
            Text(
                text = if (isRegistering) "Welcome To Sauti Ya Milimani" else "Welcome Back",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography
                    .bodyLarge


            )
            //Subtitle of Form depending on login or register
            Text(
                text = if (isRegistering) "Create Account to get started" else "Sign in to your member account",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary.copy(0.7f),
                modifier = Modifier.padding(8.dp)
            )
        }


        //Form  Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            //Check if we are in login or register mode
            if (!isRegistering) {

                //Login Form


                //Login Email Field
                InputField(
                    label = "Email",
                    placeholder = "youremail@gmail.com",
                    value = email,
                    onValueChange = { email = it },
                    type = FieldType.EMAIL
                )
                Spacer(Modifier.height(24.dp))

                //Login Password Field
                InputField(
                    label = "Password",
                    placeholder = "password",
                    value = password,
                    onValueChange = { password = it },
                    type = FieldType.PASSWORD
                )
                Spacer(Modifier.height(12.dp))

                //Login Sign In Button
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        authViewModel.loginWithEmail(email, password)
                    }
                ) {
                    Text("Sign In")
                }
                Spacer(Modifier.height(24.dp))


//--------------------------------------------------------------------------------------------------------------------------------------------------
                //OR displayer fot Continue with Google
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "OR",
                        color = MaterialTheme.colorScheme.primary.copy(0.9f),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

//--------------------------------------------------------------------------------------------------------------------------------------------------

                //Continue with Google Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GoogleSignInButton(onClick = {
                        Log.d("Google Sign In", "Tapped")
                        onStartedLoading()
                        scope.launch {
                            signInWithGoogle(
                                context,
                                navController,
                                authViewModel,
                                onDoneLoading = {
                                    onDoneLoading()
                                }
                            )
                        }

                    })
                }
                Spacer(Modifier.height(24.dp))

//--------------------------------------------------------------------------------------------------------------------------------------------------

                //Or Divider for Login Register Button
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "OR",
                        color = MaterialTheme.colorScheme.primary.copy(0.9f),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

//--------------------------------------------------------------------------------------------------------------------------------------------------

                //Button to Display Signup form
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = {
                            isRegistering = true
                            name = ""
                            email = ""
                            password = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Register")
                    }
                }


            }

//--------------------------------------------------------------------------------------------------------------------------------------------------

            //Register section
            if (isRegistering) {
                //Register Full Name Field

//                InputField(
//                    label = "Full Name",
//                    placeholder = "Enter your full name",
//                    value = name,
//                    onValueChange = { name = it },
//                    type = FieldType.TEXT
//                )
//                Spacer(Modifier.height(24.dp))


                //Register Email Field
                InputField(
                    label = "Email",
                    placeholder = "Enter your email",
                    value = email,
                    onValueChange = { email = it },
                    type = FieldType.EMAIL
                )
                Spacer(Modifier.height(24.dp))


                //Register Password Field
                InputField(
                    label = "Password",
                    placeholder = "Enter your password",
                    value = password,
                    onValueChange = { password = it },
                    type = FieldType.PASSWORD
                )
                Spacer(Modifier.height(24.dp))


                //Register Repeat Password Field
                InputField(
                    label = "Repeat Password",
                    placeholder = "Repeat password",
                    value = repeatedPassword,
                    onValueChange = { repeatedPassword = it },
                    type = FieldType.PASSWORD
                )
                Spacer(Modifier.height(24.dp))


                //Register as Member Button
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        authViewModel.signUpWithEmail(email, password)
                    }
                ) {
                    Text("Register as Member")
                }
                Spacer(Modifier.height(24.dp))

                //Or Divider for Don't have an account
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Already have a Account ?",
                        color = MaterialTheme.colorScheme.primary.copy(0.9f),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

                //Button to switch back to login mode
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = {
                            isRegistering = false
                            name = ""
                            email = ""
                            password = ""
                            repeatedPassword = ""

                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Login")
                    }
                }
            }
        }


    }


}

@Composable
fun LeaderTab(
    navController: NavController,
    authViewModel: AuthViewModel,
    context: Context,
    onStartedLoading: () -> Unit,
    onDoneLoading: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Leader Access", style = MaterialTheme.typography.titleMedium)
            Text(
                "Use your pre-registered Google account",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary.copy(0.7f)
            )
            Spacer(Modifier.height(16.dp))
            GoogleSignInButton(onClick = {
                onStartedLoading()
                scope.launch {
                    signInWithGoogle(
                        context,
                        navController,
                        authViewModel,
                        onDoneLoading = {
                            onDoneLoading()
                        }
                    )

                }

            })
            Spacer(Modifier.height(8.dp))
            Text(
                "Only pre-registered leader emails will have access",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary.copy(0.7f)
            )
        }
    }
}


data class TabItem(val title: String)


private suspend fun signInWithGoogle(
    context: Context,
    navController: NavController,
    viewModel: AuthViewModel,
    onDoneLoading: () -> Unit
) {
    Log.d("Context-Type", context.toString())


    try {
        //call function to get idToken from google
        val idToken = getIdToken(context)
        onDoneLoading()
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

