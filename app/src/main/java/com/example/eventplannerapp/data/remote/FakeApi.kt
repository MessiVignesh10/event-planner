package com.example.eventplannerapp.data.remote

import com.example.eventplannerapp.data.model.Event

class FakeEventApi : EventApi {

    private val events = mutableListOf<Event>()
    private var idCounter = 1

    override suspend fun getEvents(): List<Event> {
        return events
    }

    override suspend fun addEvent(event: Event): Event {
        val newEvent = event.copy(id = idCounter++)
        events.add(newEvent)
        return newEvent
    }
    suspend fun deleteEvent(eventId : Int): Boolean {
        return events.removeIf { it.id == eventId }

    }
}