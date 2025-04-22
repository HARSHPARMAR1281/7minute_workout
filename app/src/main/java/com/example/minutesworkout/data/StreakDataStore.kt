package com.example.minutesworkout.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "streak_preferences")

class StreakDataStore(private val context: Context) {
    private val lastWorkoutDateKey = stringPreferencesKey("last_workout_date")
    private val currentStreakKey = intPreferencesKey("current_streak")
    private val longestStreakKey = intPreferencesKey("longest_streak")

    val currentStreak: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[currentStreakKey] ?: 0
        }

    val longestStreak: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[longestStreakKey] ?: 0
        }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updateStreak() {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val todayString = today.format(formatter)

        context.dataStore.edit { preferences ->
            val lastDateString = preferences[lastWorkoutDateKey]
            val lastDate = lastDateString?.let { LocalDate.parse(it, formatter) }
            val currentStreak = preferences[currentStreakKey] ?: 0
            val longestStreak = preferences[longestStreakKey] ?: 0


            val newStreak = when {

                lastDate == null -> 1

                lastDate == today -> currentStreak

                lastDate.plusDays(1) == today -> currentStreak + 1

                else -> 1
            }


            preferences[lastWorkoutDateKey] = todayString
            preferences[currentStreakKey] = newStreak
            if (newStreak > longestStreak) {
                preferences[longestStreakKey] = newStreak
            }
        }
    }


    suspend fun resetStreak() {
        context.dataStore.edit { preferences ->
            preferences[currentStreakKey] = 0
            preferences[lastWorkoutDateKey] = ""
        }
    }
} 