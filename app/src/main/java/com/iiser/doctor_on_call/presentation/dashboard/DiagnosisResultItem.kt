package com.iiser.doctor_on_call.presentation.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

val cornerRadius = 20.dp

@Composable
fun DiagnosisResultItem(diagnosis: DiagnosisResultItemModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true, // Keeps the ripple within the shape
                    color = Color.Gray, // Adjust ripple color if needed
                    //radius = cornerRadius // Ensures ripple matches card shape

                ), // Adds ripple effect on click
                onClick = {  }
            ),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp,Color.Black),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text("Result ${diagnosis.id} | ${diagnosis.date} ",
                modifier = Modifier
                    .padding(16.dp),
                color = Color.Black
            )
        }

    }
}

@Preview
@Composable
fun DashboardResultItemPreview(){
    val exampleItem = DiagnosisResultItemModel(1, "dd,nn,yy","Example Name","Example Treatment")
    DiagnosisResultItem(exampleItem)
}