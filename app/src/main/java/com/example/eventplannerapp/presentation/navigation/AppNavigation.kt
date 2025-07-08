package com.example.eventplannerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventplannerapp.presentation.addevents.AddEventScreen
import com.example.eventplannerapp.presentation.eventlist.EventListScreen
import com.example.eventplannerapp.viewmodel.AddEventsViewModel
import com.example.eventplannerapp.viewmodel.EventListViewModel

@Composable
fun AppNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    addEventsViewModel: AddEventsViewModel,
    eventListViewModel: EventListViewModel
) {
    NavHost(navController, startDestination = Screen.EventList.route) {
        composable(Screen.EventList.route) {
            EventListScreen(
                viewModel = eventListViewModel,
                onNavigateToAdd = { navController.navigate(Screen.AddEvent.route)}
            )
        }
        composable(Screen.AddEvent.route) {
            AddEventScreen( viewModel = addEventsViewModel , onEventAdded =  { navController.popBackStack()})
        }
    }
}