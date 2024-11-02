package com.iiser.doctor_on_call

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iiser.doctor_on_call.auth.presentation.login.ui.LoginScreen
import com.iiser.doctor_on_call.core.MainAppViewModel
import com.iiser.doctor_on_call.core.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainAppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen(){
    MainScreen{
        LoginScreen()
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen {
        LoginScreen()
    }
}




//@Preview
//@Composable
//fun LoginScreenPreview(){
//    LoginScreen()
//}