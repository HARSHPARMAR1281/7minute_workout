package com.example.minutesworkout.ui.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutesworkout.R
import com.example.minutesworkout.ui.theme.PrimaryBlue
import com.example.minutesworkout.ui.theme.SecondaryGreen
import com.example.minutesworkout.data.WorkoutData
import com.example.minutesworkout.data.StreakDataStore
import com.example.minutesworkout.ui.components.WorkoutTimer
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val streakDataStore = remember { StreakDataStore(context) }
    val currentStreak by streakDataStore.currentStreak.collectAsState(initial = 0)
    val longestStreak by streakDataStore.longestStreak.collectAsState(initial = 0)
    val scope = rememberCoroutineScope()

    var isWorkoutStarted by remember { mutableStateOf(false) }
    var currentExerciseIndex by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }
    var showNextExercise by remember { mutableStateOf(false) }
    var timerKey by remember { mutableStateOf(0) }

    val exercises = WorkoutData.exercises

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A237E), // Dark blue
                        Color(0xFF3949AB), // Medium blue
                        Color(0xFF3F51B5)  // Light blue
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.9f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFE8EAF6),
                                        Color.White
                                    )
                                )
                            )
                            .padding(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "🔥 $currentStreak",
                            style = MaterialTheme.typography.headlineMedium,
                            color = PrimaryBlue
                        )
                        Text(
                            text = "Current Streak",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFFE8EAF6),
                                        Color.White
                                    )
                                )
                            )
                            .padding(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "🏆 $longestStreak",
                            style = MaterialTheme.typography.headlineMedium,
                            color = PrimaryBlue
                        )
                        Text(
                            text = "Longest Streak",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }
            }

            if (isWorkoutStarted) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Progress: ${currentExerciseIndex + 1}/${exercises.size}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LinearProgressIndicator(
                        progress = { (currentExerciseIndex + 1).toFloat() / exercises.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xFF4CAF50),
                        trackColor = Color.White.copy(alpha = 0.3f),
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            if (!isWorkoutStarted) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.9f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.main_logo),
                            contentDescription = "Workout Logo",
                            modifier = Modifier
                                .size(200.dp)
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            text = "7-Minute Workout",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color(0xFF1A237E)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "Get fit in just 7 minutes with this high-intensity workout",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = { 
                                isWorkoutStarted = true
                                timerKey = 0
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryBlue
                            )
                        ) {
                            Text("Start Workout")
                        }
                    }
                }
            } else {
                val currentExercise = exercises[currentExerciseIndex]
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.9f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = currentExercise.imageResId),
                            contentDescription = currentExercise.name,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(bottom = 16.dp)
                        )
                        
                        Text(
                            text = currentExercise.name,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF1A237E)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = currentExercise.description,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFFE8EAF6),
                                            Color.White
                                        )
                                    )
                                )
                                .clip(RoundedCornerShape(60.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            key(timerKey) {
                                WorkoutTimer(
                                    duration = currentExercise.duration,
                                    onTimerComplete = {
                                        if (currentExerciseIndex < exercises.size - 1) {
                                            currentExerciseIndex++
                                            showNextExercise = true
                                            timerKey++
                                        } else {
                                            scope.launch {
                                                streakDataStore.updateStreak()
                                            }
                                            isWorkoutStarted = false
                                            currentExerciseIndex = 0
                                            timerKey = 0
                                        }
                                    },
                                    isPaused = isPaused
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { isPaused = !isPaused },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryBlue
                                )
                            ) {
                                Text(if (isPaused) "Resume" else "Pause")
                            }
                            
                            Button(
                                onClick = {
                                    isWorkoutStarted = false
                                    currentExerciseIndex = 0
                                    timerKey = 0
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SecondaryGreen
                                )
                            ) {
                                Text("Stop")
                            }
                        }
                    }
                }
            }

            if (showNextExercise) {
                LaunchedEffect(Unit) {
                    delay(2000)
                    showNextExercise = false
                }
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SecondaryGreen.copy(alpha = 0.9f)
                    )
                ) {
                    Text(
                        text = "Next Exercise: ${exercises[currentExerciseIndex].name}",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
} 