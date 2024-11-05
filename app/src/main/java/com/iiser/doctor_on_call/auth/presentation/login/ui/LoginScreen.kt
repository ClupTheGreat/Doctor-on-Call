package com.iiser.doctor_on_call.auth.presentation.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
//import com.iiser.doctor_on_call.MainScreen
import com.iiser.doctor_on_call.auth.presentation.login.viewmodel.LoginViewModel
import com.iiser.doctor_on_call.core.screen.Screen

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
    onNavigateToSignUp: () -> Unit
) {



    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var checked by remember {
        mutableStateOf(true)
    }
    val uiState by viewModel.uiState.collectAsState()


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        ) {
        Spacer(modifier = Modifier.padding(70.dp))
        Text(
            text = AnnotatedString("Login"),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(30.dp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = uiState.userName ?: "",
            onValueChange = {
                            viewModel.onUsernameChanged(it)
            },
            label = { Text(text = "Username")},
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp, end = 30.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
//            TODO Implement trailing icon for password visibility when clicked
            value = password,
            onValueChange = {
                password = it
                viewModel.onPasswordChanged(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Password")},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth(),

        )

        Row(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Text(modifier = Modifier
                .clickable { onNavigateToSignUp() },
                text = AnnotatedString("Sign Up"),
                color = Color.Blue
            )

            Spacer(modifier = Modifier
                .weight(1f)
            )
            Text(
                text = AnnotatedString("Remember Me")
            )
            Checkbox(checked = checked, onCheckedChange = {checked = it})
        }

        OutlinedButton(
            onClick = {
                viewModel.login(username, password)
            },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
            ) {
            Text(text = "Login")

        }

    }
}
