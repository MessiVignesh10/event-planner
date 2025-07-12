package com.example.eventplannerapp.presentation.navigation

sealed class Screen (val route : String){
    object EventList : Screen("event_list")
    object AddEvent : Screen("add_event")
    object EventDetail : Screen("event_detail")
}