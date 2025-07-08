package com.example.eventplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplannerapp.data.model.Event
import com.example.eventplannerapp.data.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        loadEvents()
    }

    fun loadEvents() {
        viewModelScope.launch {
            _state.value = EventListViewModelState.Loading
            try {
                val events =
                    repository.getEvents()
                _state.value = EventListViewModelState.Success(events = events)
            } catch (e: Exception) {
                _state.value =
                    EventListViewModelState.Error(e.localizedMessage ?: "Events are not loading")
            }
        }
    }
}