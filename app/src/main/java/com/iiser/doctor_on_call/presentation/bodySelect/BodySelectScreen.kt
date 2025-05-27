package com.iiser.doctor_on_call.presentation.bodySelect

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.iiser.doctor_on_call.R
import com.iiser.doctor_on_call.presentation.screens.Screen
import kotlin.math.pow
import kotlin.math.sqrt

//enum class BodyPart(
//    val displayName: String,
//    val normalizedBounds: BodyPartBounds
//) {
//    HEAD("Head", BodyPartBounds(0.5f, 0.05f, 0.08f)),
//    NECK("Neck", BodyPartBounds(0.5f, 0.12f, 0.05f)),
//    SHOULDERS("Shoulders", BodyPartBounds(0.5f, 0.18f, 0.06f)),
//    CHEST("Chest", BodyPartBounds(0.5f, 0.22f, 0.08f)),
//    ARMS("Arms", BodyPartBounds(0.5f, 0.28f, 0.06f)),
//    HANDS("Hands", BodyPartBounds(0.5f, 0.40f, 0.05f)),
//    ABDOMEN("Abdomen", BodyPartBounds(0.5f, 0.35f, 0.07f)),
//    PELVIS("Pelvis", BodyPartBounds(0.5f, 0.52f, 0.06f)),
//    THIGHS("Thighs", BodyPartBounds(0.5f, 0.65f, 0.07f)),
//    CALVES("Calves", BodyPartBounds(0.5f, 0.85f, 0.06f)),
//    FEET("Feet", BodyPartBounds(0.5f, 0.95f, 0.05f))
//}
//
//data class BodyPartBounds(
//    val normalizedX: Float,  // x-coordinate (0.0-1.0)
//    val normalizedY: Float,  // y-coordinate (0.0-1.0)
//    val normalizedRadius: Float  // radius as proportion of container (0.0-1.0)
//)

//@Composable
//fun BodySelectScreen() {
//    // Observable list of selected body parts
//    val selectedParts = remember { mutableStateListOf<String>() }
//
//    // Track the container size to calculate absolute coordinates
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.black_wp_1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp),
//        ) {
//            // Title
//            Text(
//                "Where do you feel \nDiscomfort?",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(24.dp)
//            )
//
//            Column (modifier = Modifier
//                .fillMaxSize()
//                .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
//                .background(Color.White)) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        //.padding(horizontal = 24.dp)
//                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                        .background(Color.White)
//                ) {
//                    // The human body image with interactive regions
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .onSizeChanged { containerSize = it }
//                            .pointerInput(Unit) {
//                                detectTapGestures { offset ->
//                                    // Convert tap position to normalized coordinates
//                                    val normalizedX = offset.x / containerSize.width
//                                    val normalizedY = offset.y / containerSize.height
//
//                                    // Check if tap is within any body part's bounds
//                                    for (bodyPart in BodyPart.entries) {
//                                        val bounds = bodyPart.normalizedBounds
//                                        val distance = calculateDistance(
//                                            normalizedX, normalizedY,
//                                            bounds.normalizedX, bounds.normalizedY
//                                        )
//
//                                        if (distance <= bounds.normalizedRadius) {
//                                            // Toggle selection
//                                            if (selectedParts.contains(bodyPart.name)) {
//                                                selectedParts.remove(bodyPart.name)
//                                            } else {
//                                                selectedParts.add(bodyPart.name)
//                                            }
//                                            break // Only toggle one body part per tap
//                                        }
//                                    }
//                                }
//                            }
//                    ) {
//                        // The provided body outline image
//                        Image(
//                            painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
//                            contentDescription = "Body outline",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Fit
//                        )
//
//                        // Selection indicators for body parts
//                        Box(modifier = Modifier.fillMaxSize()) {
//                            for (selectedPartName in selectedParts) {
//                                val bodyPart = BodyPart.valueOf(selectedPartName)
//                                val bounds = bodyPart.normalizedBounds
//
//                                // Calculate absolute coordinates
//                                val centerX = bounds.normalizedX * containerSize.width
//                                val centerY = bounds.normalizedY * containerSize.height
//                                val radius = bounds.normalizedRadius *
//                                        minOf(containerSize.width, containerSize.height)
//
//                                // Selection indicator
//                                Box(
//                                    modifier = Modifier
//                                        .size(with(density) { (radius * 2).toDp() })
//                                        .offset(
//                                            x = with(density) { (centerX - radius).toDp() },
//                                            y = with(density) { (centerY - radius).toDp() }
//                                        )
//                                        .graphicsLayer(alpha = 0.7f)
//                                        .background(
//                                            color = Color(0, 150, 255, 128),
//                                            shape = androidx.compose.foundation.shape.CircleShape
//                                        )
//                                )
//                            }
//                        }
//
//
//                    }
//                }
//
//                // Button at the bottom
//                Button(
//                    onClick = { /* To be implemented later */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 24.dp, vertical = 16.dp)
//                        .height(56.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF58576D)
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = "Continue",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//            }
//
//            // Body diagram in a white rounded container
//
//        }
//    }
//}

//@Composable
//fun BodySelectScreen() {
//    // Observable list of selected body parts
//    val selectedParts = remember { mutableStateListOf<String>() }
//
//    // Track the container size to calculate absolute coordinates
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.black_wp_1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp, bottom = 16.dp),
//        ) {
//            // Title
//            Text(
//                "Where do you feel \nDiscomfort?",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(24.dp)
//            )
//
//            // Body diagram in a white rounded container
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                    .background(Color.White)
//            ) {
//                // The human body image with interactive regions
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .onSizeChanged { containerSize = it }
//                        .pointerInput(Unit) {
//                            detectTapGestures { offset ->
//                                // Convert tap position to normalized coordinates
//                                val normalizedX = offset.x / containerSize.width
//                                val normalizedY = offset.y / containerSize.height
//
//                                // Check if tap is within any body part's bounds
//                                for (bodyPart in BodyPart.values()) {
//                                    val bounds = bodyPart.normalizedBounds
//                                    val distance = calculateDistance(
//                                        normalizedX, normalizedY,
//                                        bounds.normalizedX, bounds.normalizedY
//                                    )
//
//                                    if (distance <= bounds.normalizedRadius) {
//                                        // Toggle selection
//                                        if (selectedParts.contains(bodyPart.name)) {
//                                            selectedParts.remove(bodyPart.name)
//                                        } else {
//                                            selectedParts.add(bodyPart.name)
//                                        }
//                                        break // Only toggle one body part per tap
//                                    }
//                                }
//                            }
//                        }
//                ) {
//                    // The provided body outline image
//                    Image(
//                        painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
//                        contentDescription = "Body outline",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Fit
//                    )
//
//                    // Selection indicators for body parts (smaller size)
//                    Box(modifier = Modifier.fillMaxSize()) {
//                        for (selectedPartName in selectedParts) {
//                            val bodyPart = BodyPart.valueOf(selectedPartName)
//                            val bounds = bodyPart.normalizedBounds
//
//                            // Calculate absolute coordinates
//                            val centerX = bounds.normalizedX * containerSize.width
//                            val centerY = bounds.normalizedY * containerSize.height
//                            // Make the radius smaller (70% of the original size)
//                            val radius = bounds.normalizedRadius *
//                                    minOf(containerSize.width, containerSize.height) * 0.7f
//
//                            // Neon selection indicator
//                            Box(
//                                modifier = Modifier
//                                    .size(with(density) { (radius * 2).toDp() })
//                                    .offset(
//                                        x = with(density) { (centerX - radius).toDp() },
//                                        y = with(density) { (centerY - radius).toDp() }
//                                    )
//                                    .graphicsLayer(alpha = 0.8f)
//                                    .background(
//                                        color = Color(0, 150, 255, 150),
//                                        shape = androidx.compose.foundation.shape.CircleShape
//                                    )
//                            )
//
//                            // Add a glowing border effect
//                            Box(
//                                modifier = Modifier
//                                    .size(with(density) { (radius * 2 + 4.dp.toPx()).toDp() })
//                                    .offset(
//                                        x = with(density) { (centerX - radius - 2.dp.toPx()).toDp() },
//                                        y = with(density) { (centerY - radius - 2.dp.toPx()).toDp() }
//                                    )
//                                    .graphicsLayer(alpha = 0.4f)
//                                    .background(
//                                        color = Color(0, 150, 255, 100),
//                                        shape = androidx.compose.foundation.shape.CircleShape
//                                    )
//                            )
//                        }
//                    }
//                }
//            }
//
//            // Button at the bottom
//            Button(
//                onClick = { /* To be implemented later */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 16.dp)
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF58576D)
//                ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    text = "Continue",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//    }
//}
//
//// Helper function to calculate distance between two normalized points
//private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
//    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
//}

//sealed class BodyRegion(
//    val displayName: String,
//    val selectionKey: String // Used to group related regions
//) {
//    // Single regions (centrally located)
//    class SingleRegion(
//        displayName: String,
//        val normalizedBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//
//    // Paired regions (for left/right body parts)
//    class PairedRegion(
//        displayName: String,
//        val leftBounds: BodyPartBounds,
//        val rightBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//}
//
//// Data class for defining clickable areas
//data class BodyPartBounds(
//    val normalizedX: Float,  // x-coordinate (0.0-1.0)
//    val normalizedY: Float,  // y-coordinate (0.0-1.0)
//    val normalizedRadius: Float  // radius as proportion of container (0.0-1.0)
//)
//
//// All body regions based on the image
//val bodyRegions = listOf(
//    // Head area (single)
//    BodyRegion.SingleRegion(
//        "Head",
//        BodyPartBounds(0.5f, 0.07f, 0.1f)
//    ),
//
//    // Neck area (single)
//    BodyRegion.SingleRegion(
//        "Neck",
//        BodyPartBounds(0.5f, 0.145f, 0.06f)
//    ),
//
//    // Shoulders (paired)
//    BodyRegion.PairedRegion(
//        "Shoulders",
//        BodyPartBounds(0.36f, 0.185f, 0.07f),
//        BodyPartBounds(0.64f, 0.185f, 0.07f)
//    ),
//
//    // Chest (single)
//    BodyRegion.SingleRegion(
//        "Chest",
//        BodyPartBounds(0.5f, 0.23f, 0.12f)
//    ),
//
//    // Arms (paired) - upper arms
//    BodyRegion.PairedRegion(
//        "Arms",
//        BodyPartBounds(0.28f, 0.25f, 0.08f),
//        BodyPartBounds(0.72f, 0.25f, 0.08f)
//    ),
//
//    // Hands (paired)
//    BodyRegion.PairedRegion(
//        "Hands",
//        BodyPartBounds(0.23f, 0.42f, 0.06f),
//        BodyPartBounds(0.77f, 0.42f, 0.06f)
//    ),
//
//    // Abdomen (single)
//    BodyRegion.SingleRegion(
//        "Abdomen",
//        BodyPartBounds(0.5f, 0.35f, 0.1f)
//    ),
//
//    // Pelvis (single)
//    BodyRegion.SingleRegion(
//        "Pelvis",
//        BodyPartBounds(0.5f, 0.5f, 0.1f)
//    ),
//
//    // Thighs (paired)
//    BodyRegion.PairedRegion(
//        "Thighs",
//        BodyPartBounds(0.42f, 0.63f, 0.08f),
//        BodyPartBounds(0.58f, 0.63f, 0.08f)
//    ),
//
//    // Calves (paired)
//    BodyRegion.PairedRegion(
//        "Calves",
//        BodyPartBounds(0.43f, 0.8f, 0.07f),
//        BodyPartBounds(0.57f, 0.8f, 0.07f)
//    ),
//
//    // Feet (paired)
//    BodyRegion.PairedRegion(
//        "Feet",
//        BodyPartBounds(0.43f, 0.95f, 0.06f),
//        BodyPartBounds(0.57f, 0.95f, 0.06f)
//    )
//)
//
//@Composable
//fun BodySelectScreen() {
//    // Observable list of selected body regions
//    val selectedRegions = remember { mutableStateListOf<String>() }
//
//    // Track the container size to calculate absolute coordinates
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.black_wp_1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp, bottom = 16.dp),
//        ) {
//            // Title
//            Text(
//                "Where do you feel \nDiscomfort?",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(24.dp)
//            )
//
//            // Body diagram in a white rounded container
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                    .background(Color.White)
//            ) {
//                // The human body image with interactive regions
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .onSizeChanged { containerSize = it }
//                        .pointerInput(Unit) {
//                            detectTapGestures { offset ->
//                                // Convert tap position to normalized coordinates
//                                val normalizedX = offset.x / containerSize.width
//                                val normalizedY = offset.y / containerSize.height
//
//                                // Check if tap is within any body region
//                                for (region in bodyRegions) {
//                                    var isHit = false
//
//                                    when (region) {
//                                        is BodyRegion.SingleRegion -> {
//                                            val bounds = region.normalizedBounds
//                                            val distance = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                bounds.normalizedX, bounds.normalizedY
//                                            )
//                                            isHit = distance <= bounds.normalizedRadius
//                                        }
//
//                                        is BodyRegion.PairedRegion -> {
//                                            // Check left side
//                                            val distanceLeft = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                region.leftBounds.normalizedX,
//                                                region.leftBounds.normalizedY
//                                            )
//                                            // Check right side
//                                            val distanceRight = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                region.rightBounds.normalizedX,
//                                                region.rightBounds.normalizedY
//                                            )
//
//                                            isHit = distanceLeft <= region.leftBounds.normalizedRadius ||
//                                                    distanceRight <= region.rightBounds.normalizedRadius
//                                        }
//                                    }
//
//                                    // Toggle selection if hit
//                                    if (isHit) {
//                                        if (selectedRegions.contains(region.selectionKey)) {
//                                            selectedRegions.remove(region.selectionKey)
//                                        } else {
//                                            selectedRegions.add(region.selectionKey)
//                                        }
//                                        break // Only toggle one region per tap
//                                    }
//                                }
//                            }
//                        }
//                ) {
//                    // The provided body outline image
//                    Image(
//                        painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
//                        contentDescription = "Body outline",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Fit
//                    )
//
//                    // Selection indicators for body regions
//                    Box(modifier = Modifier.fillMaxSize()) {
//                        // Draw all necessary indicators for selected regions
//                        for (region in bodyRegions) {
//                            if (selectedRegions.contains(region.selectionKey)) {
//                                when (region) {
//                                    is BodyRegion.SingleRegion -> {
//                                        // Draw single indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.normalizedBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//                                    }
//
//                                    is BodyRegion.PairedRegion -> {
//                                        // Draw left indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.leftBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//
//                                        // Draw right indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.rightBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Button at the bottom
//            Button(
//                onClick = { /* To be implemented later */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 16.dp)
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF58576D)
//                ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    text = "Continue",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//    }
//}
//
//// Helper composable to draw a selection indicator
//@Composable
//private fun DrawSelectionIndicator(
//    bounds: BodyPartBounds,
//    containerSize: IntSize,
//    density: androidx.compose.ui.unit.Density
//) {
//    val centerX = bounds.normalizedX * containerSize.width
//    val centerY = bounds.normalizedY * containerSize.height
//    val radius = bounds.normalizedRadius *
//            minOf(containerSize.width, containerSize.height) * 0.7f
//
//    // Neon selection indicator
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius).toDp() },
//                y = with(density) { (centerY - radius).toDp() }
//            )
//            .graphicsLayer(alpha = 0.8f)
//            .background(
//                color = Color(0, 150, 255, 150),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//
//    // Add a glowing border effect
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2 + 4.dp.toPx()).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius - 2.dp.toPx()).toDp() },
//                y = with(density) { (centerY - radius - 2.dp.toPx()).toDp() }
//            )
//            .graphicsLayer(alpha = 0.4f)
//            .background(
//                color = Color(0, 150, 255, 100),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//}
//
//// Helper function to calculate distance between two normalized points
//private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
//    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
//}

// TODO Works well

//sealed class BodyRegion(
//    val displayName: String,
//    val selectionKey: String // Used to group related regions
//) {
//    // Single regions (centrally located)
//    class SingleRegion(
//        displayName: String,
//        val normalizedBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//
//    // Paired regions (for left/right body parts)
//    class PairedRegion(
//        displayName: String,
//        val leftBounds: BodyPartBounds,
//        val rightBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//}
//
//// Data class for defining clickable areas
//data class BodyPartBounds(
//    val normalizedX: Float,  // x-coordinate (0.0-1.0)
//    val normalizedY: Float,  // y-coordinate (0.0-1.0)
//    val normalizedRadius: Float  // radius as proportion of container (0.0-1.0)
//)
//
//// All body regions with precise coordinates calibrated for 233x645 image
//val bodyRegions = listOf(
//    // Head area (single)
//    BodyRegion.SingleRegion(
//        "Head",
//        BodyPartBounds(0.5f, 0.07f, 0.06f)
//    ),
//
//    // Neck area (single)
//    BodyRegion.SingleRegion(
//        "Neck",
//        BodyPartBounds(0.5f, 0.15f, 0.04f)
//    ),
//
//    // Shoulders (paired)
//    BodyRegion.PairedRegion(
//        "Shoulders",
//        BodyPartBounds(0.35f, 0.19f, 0.055f),
//        BodyPartBounds(0.65f, 0.19f, 0.055f)
//    ),
//
//    // Chest (single)
//    BodyRegion.SingleRegion(
//        "Chest",
//        BodyPartBounds(0.5f, 0.28f, 0.07f)
//    ),
//
//    // Arms (paired) - upper arms
//    BodyRegion.PairedRegion(
//        "Arms",
//        BodyPartBounds(0.25f, 0.27f, 0.055f),
//        BodyPartBounds(0.75f, 0.27f, 0.055f)
//    ),
//
//    // Hands (paired)
//    BodyRegion.PairedRegion(
//        "Hands",
//        BodyPartBounds(0.18f, 0.42f, 0.05f),
//        BodyPartBounds(0.82f, 0.42f, 0.05f)
//    ),
//
//    // Abdomen (single)
//    BodyRegion.SingleRegion(
//        "Abdomen",
//        BodyPartBounds(0.5f, 0.41f, 0.07f)
//    ),
//
//    // Pelvis (single)
//    BodyRegion.SingleRegion(
//        "Pelvis",
//        BodyPartBounds(0.5f, 0.53f, 0.07f)
//    ),
//
//    // Thighs (paired)
//    BodyRegion.PairedRegion(
//        "Thighs",
//        BodyPartBounds(0.4f, 0.65f, 0.06f),
//        BodyPartBounds(0.6f, 0.65f, 0.06f)
//    ),
//
//    // Calves (paired)
//    BodyRegion.PairedRegion(
//        "Calves",
//        BodyPartBounds(0.4f, 0.82f, 0.05f),
//        BodyPartBounds(0.6f, 0.82f, 0.05f)
//    ),
//
//    // Feet (paired)
//    BodyRegion.PairedRegion(
//        "Feet",
//        BodyPartBounds(0.4f, 0.95f, 0.045f),
//        BodyPartBounds(0.6f, 0.95f, 0.045f)
//    )
//)
//
//@Composable
//fun BodySelectScreen() {
//    // Observable list of selected body regions
//    val selectedRegions = remember { mutableStateListOf<String>() }
//
//    // Track the container size to calculate absolute coordinates
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.black_wp_1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp),
//        ) {
//            // Title
//            Text(
//                "Where do you feel \nDiscomfort?",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(24.dp)
//            )
//
//            // Body diagram in a white rounded container
//
//            Column (
//                modifier = Modifier
//                    .background(Color.White)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .padding(horizontal = 24.dp)
//                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                        .background(Color.White)
//                ) {
//                    // Fixed aspect ratio container for the body image - exact 233:645 ratio
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .aspectRatio(233f/645f, matchHeightConstraintsFirst = true) // Exact aspect ratio
//                            .align(Alignment.Center) // Center in parent
//                            .onSizeChanged { containerSize = it }
//                            .pointerInput(Unit) {
//                                detectTapGestures { offset ->
//                                    // Convert tap position to normalized coordinates
//                                    val normalizedX = offset.x / containerSize.width
//                                    val normalizedY = offset.y / containerSize.height
//
//                                    // Check if tap is within any body region
//                                    for (region in bodyRegions) {
//                                        var isHit = false
//
//                                        when (region) {
//                                            is BodyRegion.SingleRegion -> {
//                                                val bounds = region.normalizedBounds
//                                                val distance = calculateDistance(
//                                                    normalizedX, normalizedY,
//                                                    bounds.normalizedX, bounds.normalizedY
//                                                )
//                                                isHit = distance <= bounds.normalizedRadius
//                                            }
//
//                                            is BodyRegion.PairedRegion -> {
//                                                // Check left side
//                                                val distanceLeft = calculateDistance(
//                                                    normalizedX, normalizedY,
//                                                    region.leftBounds.normalizedX,
//                                                    region.leftBounds.normalizedY
//                                                )
//                                                // Check right side
//                                                val distanceRight = calculateDistance(
//                                                    normalizedX, normalizedY,
//                                                    region.rightBounds.normalizedX,
//                                                    region.rightBounds.normalizedY
//                                                )
//
//                                                isHit = distanceLeft <= region.leftBounds.normalizedRadius ||
//                                                        distanceRight <= region.rightBounds.normalizedRadius
//                                            }
//                                        }
//
//                                        // Toggle selection if hit
//                                        if (isHit) {
//                                            if (selectedRegions.contains(region.selectionKey)) {
//                                                selectedRegions.remove(region.selectionKey)
//                                            } else {
//                                                selectedRegions.add(region.selectionKey)
//                                            }
//                                            break // Only toggle one region per tap
//                                        }
//                                    }
//                                }
//                            }
//                    ) {
//                        // The provided body outline image with exact 233x645 dimensions
//                        Image(
//                            painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
//                            contentDescription = "Body outline",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.Fit
//                        )
//
//                        // Selection indicators for body regions
//                        Box(modifier = Modifier.fillMaxSize()) {
//                            // Draw all necessary indicators for selected regions
//                            for (region in bodyRegions) {
//                                if (selectedRegions.contains(region.selectionKey)) {
//                                    when (region) {
//                                        is BodyRegion.SingleRegion -> {
//                                            // Draw single indicator
//                                            DrawSelectionIndicator(
//                                                bounds = region.normalizedBounds,
//                                                containerSize = containerSize,
//                                                density = density
//                                            )
//                                        }
//
//                                        is BodyRegion.PairedRegion -> {
//                                            // Draw left indicator
//                                            DrawSelectionIndicator(
//                                                bounds = region.leftBounds,
//                                                containerSize = containerSize,
//                                                density = density
//                                            )
//
//                                            // Draw right indicator
//                                            DrawSelectionIndicator(
//                                                bounds = region.rightBounds,
//                                                containerSize = containerSize,
//                                                density = density
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                Button(
//                    onClick = { /* To be implemented later */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 24.dp, vertical = 16.dp)
//                        .height(56.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF58576D)
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = "Continue",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//
//            }
//
//
//            // Button at the bottom
//
//        }
//    }
//}
//
//// Helper composable to draw a selection indicator
//@Composable
//private fun DrawSelectionIndicator(
//    bounds: BodyPartBounds,
//    containerSize: IntSize,
//    density: androidx.compose.ui.unit.Density
//) {
//    val centerX = bounds.normalizedX * containerSize.width
//    val centerY = bounds.normalizedY * containerSize.height
//    val radius = bounds.normalizedRadius *
//            minOf(containerSize.width, containerSize.height)
//
//    // Neon selection indicator
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius).toDp() },
//                y = with(density) { (centerY - radius).toDp() }
//            )
//            .graphicsLayer(alpha = 0.8f)
//            .background(
//                color = Color(0, 150, 255, 150),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//
//    // Add a glowing border effect
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2 + 4.dp.toPx()).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius - 2.dp.toPx()).toDp() },
//                y = with(density) { (centerY - radius - 2.dp.toPx()).toDp() }
//            )
//            .graphicsLayer(alpha = 0.4f)
//            .background(
//                color = Color(0, 150, 255, 100),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//}
//
//// Helper function to calculate distance between two normalized points
//private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
//    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
//}

// TODO slightly better

//sealed class BodyRegion(
//    val displayName: String,
//    val selectionKey: String // Used to group related regions
//) {
//    // Single regions (centrally located)
//    class SingleRegion(
//        displayName: String,
//        val normalizedBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//
//    // Paired regions (for left/right body parts)
//    class PairedRegion(
//        displayName: String,
//        val leftBounds: BodyPartBounds,
//        val rightBounds: BodyPartBounds
//    ) : BodyRegion(displayName, displayName)
//}
//
//// Data class for defining clickable areas
//data class BodyPartBounds(
//    val normalizedX: Float,  // x-coordinate (0.0-1.0)
//    val normalizedY: Float,  // y-coordinate (0.0-1.0)
//    val normalizedRadius: Float,  // visual radius as proportion of container (0.0-1.0)
//    val hitboxRadius: Float = normalizedRadius * 1.5f  // larger hitbox for easier selection
//)
//
//// All body regions with paired regions moved further apart to prevent clipping
//val bodyRegions = listOf(
//    // Head area (single)
//    BodyRegion.SingleRegion(
//        "Head",
//        BodyPartBounds(0.5f, 0.07f, 0.06f, 0.09f)
//    ),
//
//    // Neck area (single)
//    BodyRegion.SingleRegion(
//        "Neck",
//        BodyPartBounds(0.5f, 0.15f, 0.04f, 0.06f)
//    ),
//
//    // Shoulders (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Shoulders",
//        BodyPartBounds(0.32f, 0.19f, 0.055f, 0.08f),
//        BodyPartBounds(0.68f, 0.19f, 0.055f, 0.08f)
//    ),
//
//    // Chest (single)
//    BodyRegion.SingleRegion(
//        "Chest",
//        BodyPartBounds(0.5f, 0.28f, 0.07f, 0.1f)
//    ),
//
//    // Arms (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Arms",
//        BodyPartBounds(0.22f, 0.27f, 0.055f, 0.08f),
//        BodyPartBounds(0.78f, 0.27f, 0.055f, 0.08f)
//    ),
//
//    // Hands (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Hands",
//        BodyPartBounds(0.15f, 0.42f, 0.05f, 0.075f),
//        BodyPartBounds(0.85f, 0.42f, 0.05f, 0.075f)
//    ),
//
//    // Abdomen (single)
//    BodyRegion.SingleRegion(
//        "Abdomen",
//        BodyPartBounds(0.5f, 0.41f, 0.07f, 0.1f)
//    ),
//
//    // Pelvis (single)
//    BodyRegion.SingleRegion(
//        "Pelvis",
//        BodyPartBounds(0.5f, 0.53f, 0.07f, 0.1f)
//    ),
//
//    // Thighs (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Thighs",
//        BodyPartBounds(0.37f, 0.65f, 0.06f, 0.09f),
//        BodyPartBounds(0.63f, 0.65f, 0.06f, 0.09f)
//    ),
//
//    // Calves (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Calves",
//        BodyPartBounds(0.37f, 0.82f, 0.05f, 0.075f),
//        BodyPartBounds(0.63f, 0.82f, 0.05f, 0.075f)
//    ),
//
//    // Feet (paired) - moved further apart
//    BodyRegion.PairedRegion(
//        "Feet",
//        BodyPartBounds(0.37f, 0.95f, 0.045f, 0.07f),
//        BodyPartBounds(0.63f, 0.95f, 0.045f, 0.07f)
//    )
//)
//
//@Composable
//fun BodySelectScreen() {
//    // Observable list of selected body regions
//    val selectedRegions = remember { mutableStateListOf<String>() }
//
//    // Track the container size to calculate absolute coordinates
//    var containerSize by remember { mutableStateOf(IntSize.Zero) }
//    val density = LocalDensity.current
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.black_wp_1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp, bottom = 16.dp),
//        ) {
//            // Title
//            Text(
//                "Where do you feel \nDiscomfort?",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(24.dp)
//            )
//
//            // Body diagram in a white rounded container
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp)
//                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
//                    .background(Color.White)
//            ) {
//                // Fixed aspect ratio container for the body image - exact 233:645 ratio
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .aspectRatio(233f/645f, matchHeightConstraintsFirst = true) // Exact aspect ratio
//                        .align(Alignment.Center) // Center in parent
//                        .onSizeChanged { containerSize = it }
//                        .pointerInput(Unit) {
//                            detectTapGestures { offset ->
//                                // Convert tap position to normalized coordinates
//                                val normalizedX = offset.x / containerSize.width
//                                val normalizedY = offset.y / containerSize.height
//
//                                // Check if tap is within any body region
//                                for (region in bodyRegions) {
//                                    var isHit = false
//
//                                    when (region) {
//                                        is BodyRegion.SingleRegion -> {
//                                            val bounds = region.normalizedBounds
//                                            val distance = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                bounds.normalizedX, bounds.normalizedY
//                                            )
//                                            // Use larger hitbox radius for detection
//                                            isHit = distance <= bounds.hitboxRadius
//                                        }
//
//                                        is BodyRegion.PairedRegion -> {
//                                            // Check left side
//                                            val distanceLeft = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                region.leftBounds.normalizedX,
//                                                region.leftBounds.normalizedY
//                                            )
//                                            // Check right side
//                                            val distanceRight = calculateDistance(
//                                                normalizedX, normalizedY,
//                                                region.rightBounds.normalizedX,
//                                                region.rightBounds.normalizedY
//                                            )
//
//                                            // Use larger hitbox radius for detection
//                                            isHit = distanceLeft <= region.leftBounds.hitboxRadius ||
//                                                    distanceRight <= region.rightBounds.hitboxRadius
//                                        }
//                                    }
//
//                                    // Toggle selection if hit
//                                    if (isHit) {
//                                        if (selectedRegions.contains(region.selectionKey)) {
//                                            selectedRegions.remove(region.selectionKey)
//                                        } else {
//                                            selectedRegions.add(region.selectionKey)
//                                        }
//                                        break // Only toggle one region per tap
//                                    }
//                                }
//                            }
//                        }
//                ) {
//                    // The provided body outline image with exact 233x645 dimensions
//                    Image(
//                        painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
//                        contentDescription = "Body outline",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Fit
//                    )
//
//                    // Selection indicators for body regions
//                    Box(modifier = Modifier.fillMaxSize()) {
//                        // Draw all necessary indicators for selected regions
//                        for (region in bodyRegions) {
//                            if (selectedRegions.contains(region.selectionKey)) {
//                                when (region) {
//                                    is BodyRegion.SingleRegion -> {
//                                        // Draw single indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.normalizedBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//                                    }
//
//                                    is BodyRegion.PairedRegion -> {
//                                        // Draw left indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.leftBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//
//                                        // Draw right indicator
//                                        DrawSelectionIndicator(
//                                            bounds = region.rightBounds,
//                                            containerSize = containerSize,
//                                            density = density
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Button at the bottom
//            Button(
//                onClick = { /* To be implemented later */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 24.dp, vertical = 16.dp)
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF58576D)
//                ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    text = "Continue",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//            }
//        }
//    }
//}
//
//// Helper composable to draw a selection indicator
//@Composable
//private fun DrawSelectionIndicator(
//    bounds: BodyPartBounds,
//    containerSize: IntSize,
//    density: androidx.compose.ui.unit.Density
//) {
//    val centerX = bounds.normalizedX * containerSize.width
//    val centerY = bounds.normalizedY * containerSize.height
//    val radius = bounds.normalizedRadius *
//            minOf(containerSize.width, containerSize.height)
//
//    // Neon selection indicator
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius).toDp() },
//                y = with(density) { (centerY - radius).toDp() }
//            )
//            .graphicsLayer(alpha = 0.8f)
//            .background(
//                color = Color(0, 150, 255, 150),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//
//    // Add a glowing border effect
//    Box(
//        modifier = Modifier
//            .size(with(density) { (radius * 2 + 4.dp.toPx()).toDp() })
//            .offset(
//                x = with(density) { (centerX - radius - 2.dp.toPx()).toDp() },
//                y = with(density) { (centerY - radius - 2.dp.toPx()).toDp() }
//            )
//            .graphicsLayer(alpha = 0.4f)
//            .background(
//                color = Color(0, 150, 255, 100),
//                shape = androidx.compose.foundation.shape.CircleShape
//            )
//    )
//}
//
//// Helper function to calculate distance between two normalized points
//private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
//    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
//}
//
//@Preview
//@Composable
//fun PreviewBodySelectScreen(){
//    BodySelectScreen()
//}


// Class to define the body part, Single Region being singular body part eg: Head, Paired meaning multiples eg: arms
sealed class BodyRegion(
    val displayName: String,
    val selectionKey: String // Used to group related regions
) {
    // Single regions (centrally located)
    class SingleRegion(
        displayName: String,
        val normalizedBounds: BodyPartBounds
    ) : BodyRegion(displayName, displayName)

    // Paired regions (for left/right body parts)
    class PairedRegion(
        displayName: String,
        val leftBounds: BodyPartBounds,
        val rightBounds: BodyPartBounds
    ) : BodyRegion(displayName, displayName)
}

// Hitbox for body parts
data class BodyPartBounds(
    val normalizedX: Float,  // x-coordinate (0.0-1.0)
    val normalizedY: Float,  // y-coordinate (0.0-1.0)
    val normalizedRadius: Float,  // visual radius as proportion of container (0.0-1.0)
    val hitboxRadius: Float = normalizedRadius * 1.5f  // larger hitbox for easier selection
)

// All body regions with paired regions approximate locations according to the emulator, to be changed in future.
// TODO

val bodyRegions = listOf(
    // Head area (single)
    BodyRegion.SingleRegion(
        "Head",
        BodyPartBounds(0.5f, 0.07f, 0.06f, 0.09f)
    ),

    // Neck area (single)
    BodyRegion.SingleRegion(
        "Neck",
        BodyPartBounds(0.5f, 0.15f, 0.04f, 0.06f)
    ),

    // Shoulders (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Shoulders",
        //0.32f 0.68f
        BodyPartBounds(0.26f, 0.20f, 0.055f, 0.08f),
        BodyPartBounds(0.73f, 0.20f, 0.055f, 0.08f)
    ),

    // Chest (single)
    BodyRegion.SingleRegion(
        "Chest",
        BodyPartBounds(0.5f, 0.28f, 0.07f, 0.1f)
    ),

    // Arms (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Arms",
        BodyPartBounds(0.18f, 0.30f, 0.055f, 0.08f),
        BodyPartBounds(0.83f, 0.30f, 0.055f, 0.08f)
    ),

    // Hands (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Hands",
        BodyPartBounds(0.12f, 0.42f, 0.05f, 0.075f),
        BodyPartBounds(0.89f, 0.42f, 0.05f, 0.075f)
    ),

    // Abdomen (single)
    BodyRegion.SingleRegion(
        "Abdomen",
        BodyPartBounds(0.5f, 0.41f, 0.07f, 0.1f)
    ),

    // Pelvis (single)
    BodyRegion.SingleRegion(
        "Pelvis",
        BodyPartBounds(0.5f, 0.51f, 0.07f, 0.1f)
    ),

    // Thighs (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Thighs",
        BodyPartBounds(0.35f, 0.65f, 0.06f, 0.09f),
        BodyPartBounds(0.65f, 0.65f, 0.06f, 0.09f)
    ),

    // Calves (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Calves",
        BodyPartBounds(0.33f, 0.82f, 0.05f, 0.075f),
        BodyPartBounds(0.655f, 0.82f, 0.05f, 0.075f)
    ),

    // Feet (paired) - moved further apart
    BodyRegion.PairedRegion(
        "Feet",
        BodyPartBounds(0.35f, 0.95f, 0.045f, 0.07f),
        BodyPartBounds(0.66f, 0.95f, 0.045f, 0.07f)
    )
)

@Composable
fun BodySelectScreen(
    // Added debug mode flag
    showHitboxes: Boolean,
    controller: NavHostController
) {
    val context = LocalContext.current
    // Observable list of selected body regions
    val selectedRegions = remember { mutableStateListOf<String>() }

    // Track the container size to calculate absolute coordinates
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    var selectedRegionsText = "No regions selected"

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.black_wp_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, bottom = 16.dp),
        ) {
            // Title
            Text(
                "Where do you feel \nDiscomfort?",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier
                    .padding(24.dp)
            )

            // Body diagram in a white rounded container
            Column (modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .padding(top = 24.dp)


            ){
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Color.White)
                ) {
                    // Fixed aspect ratio container for the body image - exact 233:645 ratio
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(233f/645f, matchHeightConstraintsFirst = true) // Exact aspect ratio
                            .align(Alignment.Center) // Center in parent
                            .onSizeChanged { containerSize = it }
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    // Convert tap position to normalized coordinates
                                    val normalizedX = offset.x / containerSize.width
                                    val normalizedY = offset.y / containerSize.height

                                    // Check if tap is within any body region
                                    for (region in bodyRegions) {
                                        var isHit = false

                                        when (region) {
                                            is BodyRegion.SingleRegion -> {
                                                val bounds = region.normalizedBounds
                                                val distance = calculateDistance(
                                                    normalizedX, normalizedY,
                                                    bounds.normalizedX, bounds.normalizedY
                                                )
                                                // Use larger hitbox radius for detection
                                                isHit = distance <= bounds.hitboxRadius
                                            }

                                            is BodyRegion.PairedRegion -> {
                                                // Check left side
                                                val distanceLeft = calculateDistance(
                                                    normalizedX, normalizedY,
                                                    region.leftBounds.normalizedX,
                                                    region.leftBounds.normalizedY
                                                )
                                                // Check right side
                                                val distanceRight = calculateDistance(
                                                    normalizedX, normalizedY,
                                                    region.rightBounds.normalizedX,
                                                    region.rightBounds.normalizedY
                                                )

                                                // Use larger hitbox radius for detection
                                                isHit = distanceLeft <= region.leftBounds.hitboxRadius ||
                                                        distanceRight <= region.rightBounds.hitboxRadius
                                            }
                                        }

                                        // Toggle selection if hit
                                        if (isHit) {
                                            if (selectedRegions.contains(region.selectionKey)) {
                                                selectedRegions.remove(region.selectionKey)
                                            } else {
                                                selectedRegions.add(region.selectionKey)
                                            }
                                            break // Only toggle one region per tap
                                        }
                                    }
                                }
                            }
                    ) {
                        // The provided body outline image with exact 233x645 dimensions
                        Image(
                            painter = painterResource(id = R.drawable.humanbody_parts_white_crop),
                            contentDescription = "Body outline",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )

                        // Selection indicators for body regions
                        Box(modifier = Modifier.fillMaxSize()) {
                            // Draw all necessary indicators for selected regions
                            for (region in bodyRegions) {
                                if (selectedRegions.contains(region.selectionKey)) {
                                    when (region) {
                                        is BodyRegion.SingleRegion -> {
                                            // Draw single indicator
                                            DrawSelectionIndicator(
                                                bounds = region.normalizedBounds,
                                                containerSize = containerSize,
                                                density = density
                                            )
                                        }

                                        is BodyRegion.PairedRegion -> {
                                            // Draw left indicator
                                            DrawSelectionIndicator(
                                                bounds = region.leftBounds,
                                                containerSize = containerSize,
                                                density = density
                                            )

                                            // Draw right indicator
                                            DrawSelectionIndicator(
                                                bounds = region.rightBounds,
                                                containerSize = containerSize,
                                                density = density
                                            )
                                        }
                                    }
                                }
                            }

                            // Debug visualization for hitboxes
                            if (showHitboxes) {
                                for (region in bodyRegions) {
                                    when (region) {
                                        is BodyRegion.SingleRegion -> {
                                            // Draw hitbox visualization
                                            DrawHitboxIndicator(
                                                bounds = region.normalizedBounds,
                                                containerSize = containerSize,
                                                density = density,
                                                label = region.displayName
                                            )
                                        }

                                        is BodyRegion.PairedRegion -> {
                                            // Draw left hitbox visualization
                                            DrawHitboxIndicator(
                                                bounds = region.leftBounds,
                                                containerSize = containerSize,
                                                density = density,
                                                label = "${region.displayName} (L)"
                                            )

                                            // Draw right hitbox visualization
                                            DrawHitboxIndicator(
                                                bounds = region.rightBounds,
                                                containerSize = containerSize,
                                                density = density,
                                                label = "${region.displayName} (R)"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Button at the bottom
                Button(
                    onClick = { selectedRegionsText = if (selectedRegions.isEmpty()) {
                        "No regions selected"
                    } else {
                        selectedRegions.joinToString(", ")
                    }

                        // Create and show toast with the selected regions

                        Toast.makeText(context, selectedRegionsText, Toast.LENGTH_SHORT).show()

                        if (selectedRegions.isNotEmpty()){
                            controller.navigate("quiz_page/$selectedRegionsText")
                        }

                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6B4EFF)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

        }
    }
}

// Helper composable to draw a selection indicator
@Composable
private fun DrawSelectionIndicator(
    bounds: BodyPartBounds,
    containerSize: IntSize,
    density: androidx.compose.ui.unit.Density
) {
    val centerX = bounds.normalizedX * containerSize.width
    val centerY = bounds.normalizedY * containerSize.height
    val radius = bounds.normalizedRadius *
            minOf(containerSize.width, containerSize.height)

    // Neon selection indicator
    Box(
        modifier = Modifier
            .size(with(density) { (radius * 2).toDp() })
            .offset(
                x = with(density) { (centerX - radius).toDp() },
                y = with(density) { (centerY - radius).toDp() }
            )
            .graphicsLayer(alpha = 0.8f)
            .background(
                color = Color(0, 150, 255, 150),
                shape = androidx.compose.foundation.shape.CircleShape
            )
    )

    // Add a glowing border effect
    Box(
        modifier = Modifier
            .size(with(density) { (radius * 2 + 4.dp.toPx()).toDp() })
            .offset(
                x = with(density) { (centerX - radius - 2.dp.toPx()).toDp() },
                y = with(density) { (centerY - radius - 2.dp.toPx()).toDp() }
            )
            .graphicsLayer(alpha = 0.4f)
            .background(
                color = Color(0, 150, 255, 100),
                shape = androidx.compose.foundation.shape.CircleShape
            )
    )
}

// New helper composable to draw hitbox visualization borders
@Composable
private fun DrawHitboxIndicator(
    bounds: BodyPartBounds,
    containerSize: IntSize,
    density: androidx.compose.ui.unit.Density,
    label: String = ""
) {
    val centerX = bounds.normalizedX * containerSize.width
    val centerY = bounds.normalizedY * containerSize.height

    // Visual radius (normalized)
    val visualRadius = bounds.normalizedRadius *
            minOf(containerSize.width, containerSize.height)

    // Hitbox radius (normalized)
    val hitboxRadius = bounds.hitboxRadius *
            minOf(containerSize.width, containerSize.height)

    // Draw the visual circle with yellow border
    Canvas(
        modifier = Modifier
            .size(with(density) { (visualRadius * 2).toDp() })
            .offset(
                x = with(density) { (centerX - visualRadius).toDp() },
                y = with(density) { (centerY - visualRadius).toDp() }
            )
    ) {
        // Draw yellow circle for visual radius
        drawCircle(
            color = Color.Yellow.copy(alpha = 0.2f),
            radius = visualRadius,
            style = Stroke(width = 2f)
        )
    }

    // Draw the hitbox circle with red dashed border
    Canvas(
        modifier = Modifier
            .size(with(density) { (hitboxRadius * 2).toDp() })
            .offset(
                x = with(density) { (centerX - hitboxRadius).toDp() },
                y = with(density) { (centerY - hitboxRadius).toDp() }
            )
    ) {
        // Draw red circle for hitbox radius
        drawCircle(
            color = Color.Red.copy(alpha = 0.4f),
            radius = hitboxRadius,
            style = Stroke(
                width = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
            )
        )
    }

    // Add a small text label
    if (label.isNotEmpty()) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 8.sp,
            modifier = Modifier
                .offset(
                    x = with(density) { (centerX - hitboxRadius/2).toDp() },
                    y = with(density) { (centerY - hitboxRadius - 12.dp.toPx()).toDp() }
                )
                .background(Color.Yellow.copy(alpha = 0.5f))
                .padding(2.dp)
        )
    }
}

// Helper function to calculate distance between two normalized points
private fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
}

//@Preview
//@Composable
//fun PreviewBodySelectScreen(){
//    // Preview with hitboxes visible for debugging
//    BodySelectScreen(
//        showHitboxes = true,
//        onNavigateToQuizPageScreen = { navController.navigate(Screen.QuizPage.route) })
//}