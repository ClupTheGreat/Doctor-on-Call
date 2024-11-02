package com.iiser.doctor_on_call.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(content: @Composable () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        content()
    }
}