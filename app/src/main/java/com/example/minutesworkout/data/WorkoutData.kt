package com.example.minutesworkout.data

import com.example.minutesworkout.R

data class Exercise(
    val name: String,
    val description: String,
    val duration: Int,
    val imageResId: Int
)

object WorkoutData {
    val exercises = listOf(
        Exercise(
            "Step-ups",
            "Step up and down on a platform",
            30,
            R.drawable.step_up
        ),
        Exercise(
            "Squats",
            "Lower your body by bending knees",
            30,
            R.drawable.squats
        ),
        Exercise(
            "Triceps Dips",
            "Lower and raise your body using your arms",
            30,
            R.drawable.triceps_dip
        ),
        Exercise(
            "Plank",
            "Hold a push-up position",
            30,
            R.drawable.plank
        ),
        Exercise(
            "High Knees",
            "Run in place lifting knees high",
            30,
            R.drawable.high_knees
        ),
        Exercise(
            "Lunges",
            "Step forward and lower your body",
            30,
            R.drawable.lunges
        ),
        Exercise(
            "Push-up Rotation",
            "Push-up with rotation at the top",
            30,
            R.drawable.pushup_rotation
        ),
        Exercise(
            "Side Plank",
            "Hold a side plank position",
            30,
            R.drawable.side_plank
        )
    )
} 