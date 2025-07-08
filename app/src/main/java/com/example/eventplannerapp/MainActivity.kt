package com.example.eventplannerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.eventplannerapp.data.remote.FakeEventApi
import com.example.eventplannerapp.data.repository.EventRepository
import com.example.eventplannerapp.presentation.eventlist.EventListScreen
import com.example.eventplannerapp.presentation.navigation.AppNavBar
import com.example.eventplannerapp.ui.theme.EventPlannerAppTheme
import com.example.eventplannerapp.viewmodel.AddEventsViewModel
import com.example.eventplannerapp.viewmodel.EventListViewModel
import com.example.eventplannerapp.viewmodel.EventListViewModelState

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val repository = EventRepository(FakeEventApi())
            val eventListViewModel = EventListViewModel(repository)
            val addEventsViewModel = AddEventsViewModel(repository)
            EventPlannerAppTheme {
                val navController = rememberNavController()
                AppNavBar(
                    navController = navController,
                    addEventsViewModel = addEventsViewModel,
                    eventListViewModel = eventListViewModel,
                )
            }
        }
    }
}

