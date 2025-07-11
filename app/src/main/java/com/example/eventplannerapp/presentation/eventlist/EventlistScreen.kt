package com.example.eventplannerapp.presentation.eventlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventplannerapp.viewmodel.EventListViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.eventplannerapp.viewmodel.EventListViewModelState

@Composable
fun EventListScreen(modifier: Modifier = Modifier, viewModel: EventListViewModel = viewModel() , onNavigateToAdd :() -> Unit) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Your Events", style = MaterialTheme.typography.displaySmall)
        Button(onClick = {onNavigateToAdd()}) {
            Text("Add new Event")
        }


        when (state) {
            is EventListViewModelState.Loading -> {
                CircularProgressIndicator()
            }

            is EventListViewModelState.Success -> {
                val events = (state as EventListViewModelState.Success).events
                LazyColumn(modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                    items(events) {event ->
                        Card(modifier.fillMaxWidth().padding(10.dp).clickable(onClick = {}), shape = RoundedCornerShape(10.dp)) {
                            Column(modifier.padding(15.dp)) {
                                Column {
                                Text(event.EventName, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.ExtraBold)
                                Spacer(modifier.height(20.dp))
                                Text(
                                    "Description : ${event.EventDescription}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier.height(10.dp))
                                Row(modifier.fillMaxHeight()) {
                                    Text(
                                        "Date : ${event.EventDate}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Blue
                                    )
                                    Spacer(modifier.width(10.dp))
                                    Text(
                                        "Time : ${event.EventTime}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Blue
                                    )
                                    Spacer(modifier.weight(1f))
                                   IconButton(onClick = {
                                       println("Clicked event id to delete is ${event.id}")
                                       viewModel.deleteEvents(eventId = event.id)}) {
                                       Icon(imageVector = Icons.Default.Delete , contentDescription = "delete Event" , tint = Color.Red)
                                   }
                                }


                            }
                            }
                        }
                    }
                }
            }
            is EventListViewModelState.Error -> {
                val errorMessage = (state as EventListViewModelState.Error).message
                Text(errorMessage , color = Color.Red , style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}