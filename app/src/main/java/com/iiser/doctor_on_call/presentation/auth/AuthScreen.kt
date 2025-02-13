package com.iiser.doctor_on_call.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iiser.doctor_on_call.R
import com.iiser.doctor_on_call.presentation.dashboard.DashboardScreen

val onButtonColor = Color(0xFFFFFFFF)
val offButtonColor = Color(0xFFEEEEEF)
val greyBackgroundColor = Color(0xFFEEEEEF)
val mainSectionBackgroundColor  = Color.White

@Composable
fun AuthScreen (
        viewModel: AuthViewModel = hiltViewModel(),
        onNavigateToDashboardScreen: () -> Unit
    ) {

    var isLoginMode by remember { mutableStateOf(true) }
    val uiState by viewModel.uiState.collectAsState()

    //uiState.error?.let {
    //    error ->
    //        AlertDialog(
    //            onDismissRequest = { viewModel. }
    //        )
    //
    //}

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.black_wp_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(62.dp)
            )

            Text(
                "Welcome to",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Text(
                "Doctor On Call,",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Text(
                "Sign in-up to get self diagnosed in minutes",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                    .background(mainSectionBackgroundColor)
                    .padding(15.dp)
            ) {

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(greyBackgroundColor)

                ){
                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { isLoginMode = true },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = if (isLoginMode)
                                onButtonColor
                            else
                                offButtonColor

                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Login")
                    }

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    Button(
                        onClick = { isLoginMode = false },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = if (!isLoginMode)
                                onButtonColor
                            else
                                offButtonColor
                        )
                    ) {
                        Text("Register", color = if (!isLoginMode) Color(0xFF777777) else Color(0xFF111111))
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }

                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            "Email"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                var passwordVisible by remember { mutableStateOf(false) }

                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_lock_24),
                            "Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                                if (passwordVisible) "Hide Password" else "Show Password")

                        }
                    }

                )

                if (!isLoginMode) {
                    Spacer(modifier = Modifier.height(16.dp))
                    var confirmPasswordVisible by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        value = uiState.confirmPassword,
                        onValueChange = { viewModel.updateConfirmPassword(it) },
                        label = { Text("Confirm Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = if (confirmPasswordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_lock_24),
                                contentDescription = "Confirm Password"
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (confirmPasswordVisible)
                                            R.drawable.baseline_lock_24
                                        else
                                            R.drawable.baseline_lock_24
                                    ),
                                    contentDescription = if (confirmPasswordVisible)
                                        "Hide password"
                                    else
                                        "Show password"
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Remember me checkbox
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = uiState.rememberMe,
                        onCheckedChange = { viewModel.updateRememberMe(it) }
                    )
                    Text("Remember me")
                    Spacer(modifier = Modifier.weight(1f))
                    if (isLoginMode) {
                        TextButton(onClick = { /* Handle forgot password */ }) {
                            Text("Forgot Password?")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (isLoginMode) {
                            viewModel.login { onNavigateToDashboardScreen() }
                        } else {
                            viewModel.register { onNavigateToDashboardScreen() }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (isLoginMode) "Login" else "Register")
                }



            }


        }
        
    }
}


