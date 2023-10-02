package com.example.alarmapp.ui.screens.add


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePicker(onChange : (SimpleTime) -> Unit) {


    val timePickerState = remember {
        TimePickerState(
            is24Hour = true,
            initialHour = 0,
            initialMinute = 0
        )
    }


    onChange(SimpleTime(timePickerState.hour , timePickerState.minute))
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = 10.dp
        ) , horizontalAlignment = Alignment.CenterHorizontally) {
        TimePicker(state = timePickerState )
    }
}

data class SimpleTime(
    var hour : Int,
    var min : Int
)
