package com.example.eventplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplannerapp.data.model.Event
import com.example.eventplannerapp.data.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AddEventsViewModelState{
    object Idle : AddEventsViewModelState()
    object Loading : AddEventsViewModelState()
    object Success : AddEventsViewModelState()
    data class Error (val message : String) : AddEventsViewModelState()

}

class AddEventsViewModel (private val repository: EventRepository) : ViewModel(){

    private val _state = MutableStateFlow<AddEventsViewModelState>(AddEventsViewModelState.Idle)
    val state : StateFlow<AddEventsViewModelState> = _state


    fun addEvents(eventName  : String , eventDescription : String , eventDate : String , eventTime : String){
        viewModelScope.launch {
            _state.value = AddEventsViewModelState.Loading
            try {
                val events = Event(
                    id = System.currentTimeMillis(),
                    EventName = eventName,
                    EventDescription = eventDescription,
                    EventDate = eventDate,
                    EventTime = eventTime,
                    EventCreationTime = System.currentTimeMillis()
                )
                repository.addEvents(event = events)
                _state.value = AddEventsViewModelState.Success
            } catch (e : Exception){
                _state.value = AddEventsViewModelState.Error(e.localizedMessage ?: "Problems with adding the event !!")
            }
        }
    }
    fun reset(){
        _state.value = AddEventsViewModelState.Idle

    }
}