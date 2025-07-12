package com.example.eventplannerapp.data.repository

import com.example.eventplannerapp.data.model.Event
import com.example.eventplannerapp.data.remote.FakeEventApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EventRepository (private val api : FakeEventApi) {

    val eventsFlow : StateFlow<List<Event>>
        get() = if (api is FakeEventApi)api.events else MutableStateFlow(emptyList())

    suspend fun addEvents(event: Event) : Event{
        api.addEvent(event)
        return event
    }
    suspend fun deleteEvent(eventId : Long) : Boolean{
       return api.deleteEvent(eventId = eventId)
    }
}