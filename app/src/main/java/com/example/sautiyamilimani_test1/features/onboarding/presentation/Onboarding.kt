package com.example.sautiyamilimani_test1.features.onboarding.presentation

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.R
import com.example.sautiyamilimani_test1.presentation.navigation.Screen
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel

@Preview
@Composable
fun   OnboardingScreen(modifier:Modifier=Modifier, navController: NavController?=null, authViewModel: AuthViewModel?=null){
    Scaffold(){paddingValues->
    Surface(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = Color.Transparent
                        )
                        .clip(RoundedCornerShape(32.dp))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Church,
                        contentDescription = "Church icon",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = "SAUTI YA MILIMANI CHURCH",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

            Spacer(
                modifier = Modifier.height(48.dp)
            )

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
            ) {
                val painter = painterResource(R.drawable.bible)
                Image(
                    painter = painter,
                    contentDescription = "Bible",
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(
                modifier = Modifier.height(48.dp)
            )


            Row() {
                val title = buildAnnotatedString {
                    withStyle(
                        SpanStyle(color = Color.Black)
                    ) {
                        append("Faith in Your ")
                    }
                    withStyle(
                        SpanStyle(color = MaterialTheme.colorScheme.primary)
                    ) {
                        append("Pocket")
                    }
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold

                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Join our community to grow in faith, daily"
            )

            Spacer(
                modifier = Modifier.height(48.dp)
            )

            Button(
                onClick = {

                    navController?.navigate(Screen.SignUp)
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Create Account"
                    )
                    Text(
                        text = "Create Account"
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedButton(
                onClick = {
                    navController?.navigate(Screen.Login)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign in"
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )
            IconButton(
                onClick = {
                navController?.navigate(Screen.Home)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Continue as Guest"
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Create Account"
                    )
                }
            }
        }

        }
    }

}