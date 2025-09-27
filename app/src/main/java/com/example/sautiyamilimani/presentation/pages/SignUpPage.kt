package com.example.sautiyamilimani.presentation.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.presentation.navigation.Screen
import com.example.sautiyamilimani.presentation.viewmodels.AuthViewModel

@Composable
fun SignUpPage(modifier: Modifier =Modifier,navController: NavController,viewModel: AuthViewModel){

    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        if(loginState is Resource.Success){
            navController.navigate(Screen.Home.route){
                popUpTo(Screen.Login.route) {  inclusive=true}
            }
        }
    }

    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("SignUp", fontSize = 32.sp)

        Spacer(modifier= Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email=it
            },
            label={Text("Email")}
        )
        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password=it
            },
            label={Text("Password")}
        )

        Spacer(modifier= Modifier.height(16.dp))
        Button(
            onClick = {}
        ){
            Text("Create Account")
        }


        Spacer(modifier= Modifier.height(16.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(text="Already have an account?")
            TextButton(
                onClick = {
                    navController.navigate("loginPage")
                },
            ){
                Text("Login")
            }
        }

    }

}