package com.iiser.doctor_on_call

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.iiser.doctor_on_call.presentation.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController = navController)

        }
    }
}


//@Composable
//fun MainScreen_act(content: @Composable () -> Unit){
//    MainScreen{
//        LoginScreen()
//    }
//}

//@Preview
//@Composable
//fun MainScreenPreview(){
//    MainScreen {
//        SignUpScreen()
//    }
//}




//@Preview
//@Composable
//fun LoginScreenPreview(){
//    LoginScreen()
//}