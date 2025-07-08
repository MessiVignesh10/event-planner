package com.example.eventplannerapp.data.remote

import com.example.eventplannerapp.data.model.Event

class FakeEventApi : EventApi {

    private val events = mutableListOf<Event>()


    override suspend fun getEvents(): List<Event> {
        return events
    }

    override suspend fun addEvent(event: Event): Event {
        events.add(event)
        return event
    }
}