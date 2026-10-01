package com.example.lifetracker.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityDialog(
    onDismiss: () -> Unit,
    onSave: (categoryName: String, activityName: String, count: Int, distance: Double) -> Unit
){
    var categotyName by remember {mutableStateOf("")}
    var activityName by remember {mutableStateOf("")}
    var countValue by remember {mutableStateOf("0")}
    var distanceValue by remember {mutableStateOf("0.0")}

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add new activity") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = categotyName,
                    onValueChange = {categotyName = it},
                    label = {Text("Category")},
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = activityName,
                    onValueChange = {activityName = it},
                    label = {Text("Activity")},
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = countValue,
                    onValueChange = {countValue = it},
                    label = {Text("Amount of activity")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = distanceValue,
                    onValueChange = {distanceValue = it},
                    label = {Text("Distance (km)")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val count = countValue.toIntOrNull() ?: 0
                    val distance = distanceValue.toDoubleOrNull() ?: 0.0
                    if(categotyName.isNotBlank() && activityName.isNotBlank()){
                        onSave(categotyName.trim(), activityName.trim(), count, distance)
                    }
                }
            ){
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


