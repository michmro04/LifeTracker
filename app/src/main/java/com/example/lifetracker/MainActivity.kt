package com.example.lifetracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifetracker.ui.theme.TrainingTrackerTheme
import com.example.lifetracker.model.TrackedItem
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import com.example.lifetracker.model.Category
import com.example.lifetracker.model.MeasurementType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.lifecycleScope
import com.example.lifetracker.data.AppDatabase
import com.example.lifetracker.data.TrackedItemEntity
import com.example.lifetracker.ui.home.HomeScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrainingTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        onAddActivityClick = {
                            Toast.makeText(
                                this,
                                "Tutaj panel dodawania aktywności!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TrackerScreen(){
    val trackedItems = remember {
        mutableStateListOf(
            TrackedItem("1","Push ups", Category.SPORT, MeasurementType.Count(0)),
            TrackedItem("2","Rubik's cube", Category.HOBBY, MeasurementType.Time(1,30)),
            TrackedItem("3","Running", Category.SPORT, MeasurementType.Distance(10.0))
        )
    }

    LazyColumn {
        items(trackedItems) { item ->
            TrackedItemRow(item = item)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseScreenPreview(){
    TrackerScreen()
}

@Composable
fun TrackedItemRow(item: TrackedItem){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(modifier = Modifier.weight(1f))
        {
             Text(text = item.name, style= MaterialTheme.typography.titleMedium)
             Text(text = item.category.name, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }

        when (val measurementType = item.measurementType) {
            is MeasurementType.Count -> {
                var count by remember { mutableIntStateOf(measurementType.currentCount) }
                Text(text = count.toString(), modifier = Modifier.padding(end = 16.dp))
                Button(onClick = {
                    count++
                    measurementType.currentCount = count
                }) {
                    Text(text = "+")
                }
            }

            is MeasurementType.Time -> {
                var hours by remember { mutableStateOf(measurementType.hours.toString()) }
                var minutes by remember { mutableStateOf(measurementType.minutes.toString()) }

                OutlinedTextField(
                    value = hours,
                    onValueChange = { newValue ->
                        hours = newValue
                        measurementType.hours = newValue.toIntOrNull() ?: 0
                    },
                    label = {Text("h")},
                    modifier = Modifier.width(70.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = minutes,
                    onValueChange = { newValue ->
                        minutes = newValue
                        measurementType.minutes = newValue.toIntOrNull() ?: 0
                    },
                    label = {Text("min")},
                    modifier = Modifier.width(70.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }

            is MeasurementType.Distance -> {
                var distance by remember {mutableStateOf(measurementType.kilometers.toString())}

                OutlinedTextField(
                    value = distance,
                    onValueChange = { newValue ->
                        distance = newValue
                        measurementType.kilometers = newValue.toDoubleOrNull() ?: 0.0
                    },
                    label = {Text("km")},
                    modifier = Modifier.width(100.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TrackedItemRowPreview(){
    TrackedItemRow(TrackedItem("1", "Push ups", Category.SPORT, MeasurementType.Count(0)))
}


