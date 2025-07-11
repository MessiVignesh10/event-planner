package com.example.eventplannerapp.presentation.addevents

import android.health.connect.datatypes.units.Length
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventplannerapp.viewmodel.AddEventsViewModel
import com.example.eventplannerapp.viewmodel.AddEventsViewModelState
import java.util.Calendar
import kotlin.math.sin
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import com.example.eventplannerapp.R
import java.util.Locale


@Composable
fun AddEventScreen(modifier: Modifier = Modifier, viewModel: AddEventsViewModel , onEventAdded :() -> Unit) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)


    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val formattedDate = "$selectedDayOfMonth ${calendar.getDisplayName(Calendar.MONTH ,
                Calendar.SHORT , Locale.getDefault())} $selectedYear"
            date = formattedDate
        },year,month,day).apply {
            datePicker.minDate = calendar.timeInMillis
    }

    val timePickerDialog = TimePickerDialog(context,{_,selectedHour,selectedMinute ->
        time = "$selectedHour $selectedMinute "
    },hour,minute,true)





    LaunchedEffect(state) {
        when(state){
            is AddEventsViewModelState.Success -> {
                onEventAdded()
                viewModel.reset()
            }
            is AddEventsViewModelState.Error -> {
                Toast.makeText(context,"Something went wrong", Toast.LENGTH_LONG).show()
                viewModel.reset()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(20.dp)
            .border(
                width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Event Name", color = Color.Gray) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Event Description", color = Color.Gray) },
                singleLine = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = date,
                onValueChange = { },
                readOnly = true,
                label = { Text("Event Date", color = Color.Gray) },
                singleLine = true,
                trailingIcon = {
                    Text(
                        "\uD83D\uDCC5",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable(onClick = { datePickerDialog.show() })
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = time,
                onValueChange = { },
                readOnly = true,
                label = { Text("Event Time", color = Color.Gray) },
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = null,modifier = Modifier.padding(end = 10.dp).clickable(onClick = {timePickerDialog.show()})
                    )
                }

            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addEvents(
                        eventName = name,
                        eventDescription = description,
                        eventDate = date,
                        eventTime = time
                    )
                },
                modifier.size(200.dp, 40.dp),
                enabled = if (name.isNotBlank() && description.isNotBlank() && date.isNotBlank() && time.isNotBlank()) true else false,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                elevation = ButtonDefaults.elevatedButtonElevation(pressedElevation = 20.dp)
            ) {
                Text("Add")
            }

        }
    }
}

