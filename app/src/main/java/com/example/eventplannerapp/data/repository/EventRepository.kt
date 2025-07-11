package com.example.eventplannerapp.data.repository

import com.example.eventplannerapp.data.model.Event
import com.example.eventplannerapp.data.remote.FakeEventApi

class EventRepository (private val api : FakeEventApi) {

    suspend fun getEvents() : List<Event>{
        return api.getEvents()
    }

    suspend fun addEvents(event: Event) : Event{
        api.addEvent(event)
        return event
    }
    suspend fun deleteEvent(eventId : Long) : Boolean{
       return api.deleteEvent(eventId = eventId)
    }
}