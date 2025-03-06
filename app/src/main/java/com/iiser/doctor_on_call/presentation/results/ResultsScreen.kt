package com.iiser.doctor_on_call.presentation.results

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
import com.iiser.doctor_on_call.R
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.presentation.dashboard.DiagnosisResultItem

@Composable
fun ResultsScreen(result: DiagnosisResultItemModel?) {
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


                    Row {
                        Text(
                            text = "Diagnostic ",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )

                        Text(
                            text = "Result",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF6B4EFF)
                        )

//                        Text(
//                            text = "?",
//                            style = MaterialTheme.typography.headlineMedium,
//                            color = Color.White
//                        )
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
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                    .background(Color.White)
                    .padding( start = 24.dp, end = 24.dp, top = 24.dp )
            ) {
                Text(
                    text = "Disease: ${result?.diseaseName ?: "Unknown"}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Treatment:\n${result?.diseaseTreatment ?: "No treatment available"}",
                    fontSize = 18.sp,
                    color = Color.Blue
                )
            }

        }


    }
}