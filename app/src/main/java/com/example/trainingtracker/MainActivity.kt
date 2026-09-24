package com.example.trainingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trainingtracker.ui.theme.TrainingTrackerTheme
import com.example.trainingtracker.model.Exercise
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExerciseList()
                }
            }
        }
    }
}

@Composable
fun ExerciseList() {
    val myExercises = listOf(
        Exercise("Pompki", 5),
        Exercise("Wykroki", 10),
        Exercise("Brzuszki", 15)
    )

    LazyColumn() {
        items(myExercises) { singleExercise ->
            ExerciseRow(exercise = singleExercise)
        }
    }
}


@Composable
fun ExerciseRow(exercise: Exercise){
    var reps by remember { mutableIntStateOf(exercise.reps) }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = exercise.name)
        Spacer(modifier = Modifier.weight(1f))


        Button(onClick = {
            reps+=1
            exercise.reps = reps
        }) {
            Text(text = reps.toString())
            Text(text = " [+]")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseRowPreview(){
    ExerciseRow(Exercise("Pompki", 5))
}


