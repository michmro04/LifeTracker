package com.example.lifetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_items")
data class TrackedItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val activityId: Long,
    val countValue: Int = 0,
    val timeHours: Int = 0,
    val timeMinutes: Int = 0,
    val distanceKm: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)