package com.example.eventplannerapp.data.remote

import com.example.eventplannerapp.data.model.Event

interface EventApi {
    suspend fun getEvents(): List<Event>

    suspend fun addEvent(event: Event) : Event

    suspend fun deleteEvent(eventId : Int) : Boolean

}