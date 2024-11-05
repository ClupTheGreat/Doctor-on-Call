package com.iiser.doctor_on_call

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iiser.doctor_on_call.auth.presentation.login.ui.LoginScreen
import com.iiser.doctor_on_call.auth.presentation.signup.ui.SignUpScreen
import com.iiser.doctor_on_call.core.MainAppViewModel
import com.iiser.doctor_on_call.core.MainScreen
import com.iiser.doctor_on_call.core.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainAppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            With Navigation navhost
            Navigation()

//            OLD CODE
//            MainScreen_act { LoginScreen()}
//             MainScreen(){
//                 LoginScreen()
//             }
        }
    }
}


//@Composable
//fun MainScreen_act(content: @Composable () -> Unit){
//    MainScreen{
//        LoginScreen()
//    }
//}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen {
        SignUpScreen()
    }
}




//@Preview
//@Composable
//fun LoginScreenPreview(){
//    LoginScreen()
//}