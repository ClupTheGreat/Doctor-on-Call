package com.iiser.doctor_on_call.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iiser.doctor_on_call.R
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onComplete: (DiagnosisResultItemModel?) -> Unit,
    selectedRegionsText: String?
) {

    val uiState by viewModel.uiState.collectAsState()
    val currentQuestion = viewModel.getCurrentQuestion()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Back",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Question number
        Text(
            text = "Question ${uiState.currentQuestionIndex + 1}",
            color = Color(0xFF6B4EFF),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        currentQuestion?.let { question ->
            // Question text
            Text(
                text = question.question,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.pain_illustration),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Options
            val selectedOptions = uiState.selectedOptionsPerQuestion[uiState.currentQuestionIndex] ?: emptySet()

            question.options.forEach { option ->
                val isSelected = selectedOptions.contains(option)
                Button(
                    onClick = { viewModel.toggleOption(option) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface,
                        contentColor = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else Color.Gray
                    )
                ) {
                    Text(
                        text = option,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (uiState.currentQuestionIndex > 0) {
                    Button(
                        onClick = { viewModel.onPreviousQuestion() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Previous")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }

                Button(
                    onClick = { viewModel.onNextQuestion() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        if (uiState.currentQuestionIndex == 5)
                            "Finish"
                        else
                            "Next"
                    )
                }
            }
        }
    }

    // Handle completion
    LaunchedEffect(uiState.isComplete) {
        if (uiState.isComplete) {
            onComplete(uiState.dataForResultsPage)
        }
    }

    // Error handling
    uiState.error?.let { error ->
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Error") },
            text = { Text(error) },
            confirmButton = {
                TextButton(onClick = onBackClick) {
                    Text("OK")
                }
            }
        )
    }

}