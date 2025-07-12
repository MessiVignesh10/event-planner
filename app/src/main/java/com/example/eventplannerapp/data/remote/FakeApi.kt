package com.example.eventplannerapp.data.remote

import com.example.eventplannerapp.data.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeEventApi : EventApi {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events : StateFlow<List<Event>> = _events

    override suspend fun getEvents(): List<Event> {
        return _events.value
    }

    override suspend fun addEvent(event: Event): Event {
        val updated = _events.value.toMutableList()
        updated.add(event)
        _events.value = updated
        return event
    }
    override suspend fun deleteEvent(eventId : Long): Boolean {
        val updated = _events.value.toMutableList()
        val removed = updated.removeIf { it.id == eventId }
        _events.value = updated
        return removed

    }
}