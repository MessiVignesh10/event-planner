package com.example.eventplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplannerapp.data.model.Event
import com.example.eventplannerapp.data.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class EventListViewModelState {
    object Loading : EventListViewModelState()
    data class Success(val events: List<Event>) : EventListViewModelState()
    data class Error(val message: String) : EventListViewModelState()
}

class EventListViewModel(private val repository: EventRepository) : ViewModel() {

    private val _state = MutableStateFlow<EventListViewModelState>(EventListViewModelState.Loading)
    val state: StateFlow<EventListViewModelState> = _state


init {
    observeEvents()
}

    private fun observeEvents(){
        viewModelScope.launch {
            repository.eventsFlow
                .onStart {
                    _state.value = EventListViewModelState.Loading
                }
                .catch { e ->
                    _state.value = EventListViewModelState.Error(e.localizedMessage ?: "Unknown Error")
                }
                .collect { eventList ->
                    _state.value = EventListViewModelState.Success(events = eventList)
                }
        }
    }


    fun deleteEvents(eventId: Long){
        viewModelScope.launch {
            println("Deleting event with $eventId")
            val success = repository.deleteEvent(eventId = eventId)
            println("Deletion Success $success")
            if (success){

            }
        }
    }

}