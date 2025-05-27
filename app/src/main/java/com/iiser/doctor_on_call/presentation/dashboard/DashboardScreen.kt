package com.iiser.doctor_on_call.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iiser.doctor_on_call.R
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    userName: String = "John",
    onNavigateToBodySelectScreen: () -> Unit,
    onResultClick: (DiagnosisResultItemModel) -> Unit
//TODO  include some navigation code
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBBBBB))
    ) {
        // Background pattern
        Image(
            painter = painterResource(id = R.drawable.black_wp_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(24.dp)
        ) {
            // Top bar with profile
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column () {

                    Spacer(modifier = Modifier.height(175.dp))

                    Text(
                        text = "Hello, $userName",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Row {
                        Text(
                            text = "How are you ",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )

                        Text(
                            text = "Feeling",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF6B4EFF)
                        )

                        Text(
                            text = "?",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                    }
                }

                IconButton(
                    onClick = { /*Do nothing for now */},
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onNavigateToBodySelectScreen() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp)
                    .shadow(elevation = 8.dp,
                        shape = RoundedCornerShape(26.dp),
                        ambientColor = Color(0xFF8A75FF),
                        spotColor = Color(0xFF8A75FF) ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6B4EFF)
                ),
                shape = RoundedCornerShape(26.dp),
            ) {
                Text(
                    text = "Take Test",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            val pastDiagnoses = viewModel.pastDiagnoses

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                    .background(Color.White)
                    .padding( start = 24.dp, end = 24.dp, top = 24.dp )
            ) {

//                val pastDiagnoses = listOf(
//                    DiagnosisResultItemModel(1, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(2, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(3, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(4, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(5, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(6, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(7, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(8, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(9, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(10, "dd,mm,yy","Example Name","Example Treatment"),
//                    DiagnosisResultItemModel(11, "dd,mm,yy","Example Name","Example Treatment")
//                )

                items(pastDiagnoses) { item: DiagnosisResultItemModel ->
                    Spacer(modifier = Modifier.height(8.dp))
                    DiagnosisResultItem(diagnosis = item, onClick = { onResultClick(item) })
                }


            }
        }


    }
}

