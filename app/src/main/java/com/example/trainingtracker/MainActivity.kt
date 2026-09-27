package com.example.trainingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExerciseScreen()
                }
            }
        }
    }
}

@Composable
fun ExerciseScreen(){
    val exercise = remember {
        mutableStateListOf(
            Exercise("Pompki", 5),
            Exercise("Wykroki", 10),
            Exercise("Brzuszki", 15)
        )
    }

    var newExerciseName by remember {mutableStateOf("")}

    Column {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = newExerciseName,
                onValueChange = {newExerciseName = it},
                label = {Text("Nazwa ćwiczenia")},
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if(newExerciseName.isNotBlank()){
                        exercise.add(Exercise(newExerciseName.trim(), 0))
                        newExerciseName = ""
                    }
                }
            ) {
                Text(text = "Dodaj")
            }
        }

        LazyColumn{
            items(exercise){ singleExercise ->
                ExerciseRow(exercise = singleExercise)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseScreenPreview(){
    ExerciseScreen()
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
            Text(text = " | +")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseRowPreview(){
    ExerciseRow(Exercise("Pompki", 5))
}


