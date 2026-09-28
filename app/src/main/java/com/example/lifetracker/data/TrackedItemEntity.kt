package com.example.lifetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_items")
data class TrackedItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val categoryName: String,
    val measurementTypeString: String,
    val countValue: Int = 0,
    val timeHours: Int = 0,
    val timeMinutes: Int = 0,
    val distanceKm: Double = 0.0
)