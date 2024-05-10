package com.example.nixfit

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nixfit.domain.manager.AppEntryUseCases
import com.example.nixfit.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases : AppEntryUseCases
): ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init{
        appEntryUseCases.readAppEntry().onEach {homeScreen ->
            startDestination = if (homeScreen){
                Route.TempNavigation.route
            } else{
                Route.AppStartNavigation.route
            }
            delay(300)
                splashCondition = false
        }.launchIn(viewModelScope)
    }
}