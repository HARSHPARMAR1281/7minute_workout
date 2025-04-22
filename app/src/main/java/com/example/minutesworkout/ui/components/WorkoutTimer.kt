package com.example.minutesworkout.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.minutesworkout.ui.theme.PrimaryBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WorkoutTimer(
    duration: Int,
    onTimerComplete: () -> Unit,
    isPaused: Boolean = false
) {
    var remainingTime by remember { mutableStateOf(duration) }
    var isActive by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isPaused) {
        isActive = !isPaused
    }

    LaunchedEffect(key1 = isActive, key2 = remainingTime) {
        if (isActive && remainingTime > 0) {
            delay(1000)
            remainingTime--
        } else if (remainingTime == 0) {
            onTimerComplete()
        }
    }

    Text(
        text = "$remainingTime",
        style = MaterialTheme.typography.displayLarge,
        color = PrimaryBlue
    )
} 