package com.iiser.doctor_on_call.auth.presentation.login.ui

import android.widget.CheckBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.iiser.doctor_on_call.MainScreen

@Composable
fun LoginScreen() {

    viewModelFactory {  }

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var checked by remember {
        mutableStateOf(true)
    }

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
            value = username, onValueChange = {},
            label = { Text(text = "Username")},
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp, end = 30.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password, onValueChange = {},
            label = { Text(text = "Password")},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                )

        Row(modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Text(
                text = AnnotatedString("Sign Up")
            )
            Spacer(modifier = Modifier
                .weight(1f)
            )
            Text(
                text = AnnotatedString("Remember Me"))
            Checkbox(checked = checked, onCheckedChange = {checked = it})
        }

        OutlinedButton(onClick = {},
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
            ) {
            Text(text = "Login")

        }

    }
}
