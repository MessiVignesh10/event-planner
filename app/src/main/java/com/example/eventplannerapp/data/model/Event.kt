package com.example.eventplannerapp.data.model

data class Event(
    val id: Long,
    val EventName: String,
    val EventDescription: String,
    val EventDate: String,
    val EventTime: String,
    val EventCreationTime : Long
)