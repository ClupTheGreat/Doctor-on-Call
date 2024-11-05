package com.iiser.doctor_on_call.auth.presentation.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iiser.doctor_on_call.auth.presentation.signup.viewmodel.SignUpViewModel
import com.iiser.doctor_on_call.core.MainScreen

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var phone_number by remember {
        mutableStateOf(null)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Spacer(modifier = Modifier.padding(70.dp))
        Text(
            text = AnnotatedString("Sign Up"),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(30.dp)
        )

        OutlinedTextField(
            value = "",//uiState.userName ?: "",
            onValueChange = {
                            viewModel.onUsernameChange(it)
//                viewModel.onUsernameChanged(it)
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
                            viewModel.onPasswordChange(it)
//                viewModel.onPasswordChanged(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Password")},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth(),

            )


        OutlinedTextField(
            value = "",//uiState.userName ?: "",
            onValueChange = { viewModel.onEmailChange(it)
//                viewModel.onUsernameChanged(it)
            },
            label = { Text(text = "Email")},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = "",//uiState.userName ?: "",
            onValueChange = {viewModel.onPhoneNumber(it)
//                viewModel.onUsernameChanged(it)
            },
            label = { Text(text = "Phone Number")},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
        )

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                .fillMaxWidth()
            ) {
            Text(text = "Sign Up")
        }
    }

}