package com.example.lifetracker.model

sealed class MeasurementType {
    //amoun of habit e.g. amount of exercises, read books, solved rubiks cubes, baked cakes
    data class Count (var currentCount: Int = 0) : MeasurementType()

    //time of habit e.g. focus, studying, reading
    data class Time (var hours: Int = 0, var minutes: Int = 0) : MeasurementType()

    //distance of habit e.g. walking, running, driving
    data class Distance(var kilometers: Double = 0.0) : MeasurementType()
}

data class TrackedItem(
    val Id: String,     // unique ID
    val name: String,   //name of habit
    val category: Category,     //category of habit
    val measurementType: MeasurementType
)

enum class Category {SPORT, HOBBY, LEARNING, WORK, OTHER}