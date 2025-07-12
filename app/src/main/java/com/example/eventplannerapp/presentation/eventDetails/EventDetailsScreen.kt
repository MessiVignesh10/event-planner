package com.example.eventplannerapp.presentation.eventDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventplannerapp.data.model.Event

@Composable
fun EventDetailsScreen(modifier: Modifier = Modifier,event: Event, onBack : () -> Unit) {
    Column (modifier.fillMaxSize().padding(16.dp)){
        Text("Title ${event.EventName}")
        Spacer(modifier.height(10.dp))
         Text("Description ${event.EventDescription}")
        Spacer(modifier.height(10.dp))
         Text("Event CreationDate ${event.EventCreationTime}")
        Spacer(modifier.height(10.dp))
         Text("Event Time ${event.EventTime}")
        Spacer(modifier.height(10.dp))
         Text("EventDate ${event.EventDate}")
        Spacer(modifier.height(10.dp))

    }
}