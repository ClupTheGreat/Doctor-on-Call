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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@Composable
fun ResultsScreen(result: DiagnosisResultItemModel?, onNavigateToDashboardScreen: () -> Unit,  viewModel: ResultsViewModel = hiltViewModel()) {

    val showDialog = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.deleteSuccess) {
        if (uiState.deleteSuccess) {
            viewModel.resetDeleteSuccess()
            onNavigateToDashboardScreen()
        }
    }

    uiState.error?.let { error ->
        LaunchedEffect(error) {
            // TODO if error
            viewModel.clearError()
        }
    }

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

                    Spacer(modifier = Modifier.height(105.dp))


                    Row {
                        Text(
                            text = "Diagnostic ",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )

                        Text(
                            text = "Result",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color(0xFF6B4EFF)
                        )

//                        Text(
//                            text = "?",
//                            style = MaterialTheme.typography.headlineMedium,
//                            color = Color.White
//                        )
                    }
                    Text(
                            text = "Below is the result based on your answers",
                            style = MaterialTheme.typography.headlineSmall,
                        fontSize = 15.sp,
                            color = Color.Gray
                        )
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
                    text = "\uD83E\uDE7A Diagnosis:",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Text(
                    text = result?.diseaseName ?: "Unknown",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "\uD83D\uDCCB Recommended Treatments:\n\n${sanitizeTextForDisplay(result?.diseaseTreatment ?: "No treatment available")}",
                    fontSize = 18.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                Button(
                    onClick = { onNavigateToDashboardScreen()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .shadow(elevation = 8.dp,
                            shape = RoundedCornerShape(26.dp),
                            ambientColor = Color(0xFF8A75FF),
                            spotColor = Color(0xFF8A75FF) ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6B4EFF))
                ) { Text(
                    text = "Return to dashboard",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ) }

                Button(
                    onClick = { showDialog.value = true },
                    enabled = !uiState.isDeleting, // Disable while deleting
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(26.dp),
                            ambientColor = Color.Red,
                            spotColor = Color.Red
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(
                        text = if (uiState.isDeleting) "Deleting..." else "Delete",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }

                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    result?.id?.let { id ->
                                        viewModel.deleteResult(id) {
                                            showDialog.value = false
                                        }
                                    }
                                },
                                enabled = !uiState.isDeleting
                            ) {
                                Text(
                                    text = if (uiState.isDeleting) "Deleting..." else "Delete",
                                    color = Color.Red
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showDialog.value = false },
                                enabled = !uiState.isDeleting
                            ) {
                                Text("Cancel")
                            }
                        },
                        title = {
                            Text("Confirm Deletion")
                        },
                        text = {
                            Text("Are you sure you want to delete this result?")
                        }
                    )
                }





            }



        }




    }


}


fun removeUnsupportedCharacters(text: String): String {
    // Remove emojis using Unicode blocks for emojis
    val emojiRegex = Regex(
        "[\\uD83C-\\uDBFF\\uDC00-\\uDFFF" + // surrogate pairs
                "\\u2600-\\u27BF" +                // Misc symbols
                "\\uFE00-\\uFE0F" +                // Variation Selectors
                "\\u200D" +                        // Zero Width Joiner
                "\\uFFFD" +                        // Replacement character
                "]"
    )
    return text.replace(emojiRegex, "")
}

fun sanitizeTextForDisplay(text: String): String {
    // Unicode replacement character used when rendering unsupported characters
    val replacementChar = '\uFFFD'

    // Remove lines that contain replacement characters
    return text
        .split("\n")
        .filterNot { it.contains(replacementChar) }
        .joinToString("\n")
}

