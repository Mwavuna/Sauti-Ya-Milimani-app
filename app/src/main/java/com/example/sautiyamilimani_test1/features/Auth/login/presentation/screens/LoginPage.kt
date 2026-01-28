package com.example.sautiyamilimani_test1.features.Auth.login.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Church
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import com.example.sautiyamilimani_test1.presentation.components.FieldType
import com.example.sautiyamilimani_test1.presentation.components.GoogleSignInButton
import com.example.sautiyamilimani_test1.presentation.components.InputField
//import com.example.sautiyamilimani_test1.presentation.pages.auth.signInWithGoogle
import kotlinx.coroutines.launch

@Preview
@Composable
fun LoginScreen() {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            LoginSection()
            SignUpSection()
        }
    }
}

@Composable
fun LoginSection() {
    Card(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4 .dp)

        ){
    Column(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Card(
            modifier = Modifier.clip((CircleShape)),
            elevation = CardDefaults.cardElevation(32.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Church,
                contentDescription = "Church Icon",
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp),
            )
        }
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "Sign in to connect with community",
            color = MaterialTheme.colorScheme.primary.copy(.7f)
        )
        Spacer(Modifier.height(16.dp))

        Spacer(Modifier.height(16.dp))

        InputField(
            label = "EMAIL ADDRESS",
            placeholder = "you@example.com",
            value = "",
            onValueChange = {},
            type = FieldType.EMAIL,
            autoFocus = true
        )
        Spacer(Modifier.height(16.dp))

        InputField(
            label = "PASSWORD",
            placeholder = "Enter your password",
            value = "",
            onValueChange = {},
            type = FieldType.PASSWORD,
            autoFocus = false
        )
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.primary.copy(.7f)
            )

        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {}
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Sign In")
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Sign In"
                )
            }
        }

        Spacer(Modifier.height(16.dp))


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
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val scope = LocalContext.current
            GoogleSignInButton(onClick = {
                AdminDashboard()
            })
//                    Log.d("Google Sign In", "Tapped")
//                    //onStartedLoading()
//                    scope.launch {
//                        signInWithGoogle(
//                            context,
//                            navController,
//                            authViewModel,
//                            onDoneLoading = {
//                                onDoneLoading()
//                            }
//                        )
//                    }
//
//                })
        }
    }}
}


@Composable
fun SignUpSection() {


        IconButton(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()


            ) {
            Text(text = "New here?")
            Text(
                text = "Create an Account",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }

    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = "Privacy Policy")
        Spacer(Modifier.width(8.dp))
        Text(text = "Terms of Service")
    }

}