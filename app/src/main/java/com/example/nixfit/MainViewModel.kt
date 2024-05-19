package com.example.nixfit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nixfit.domain.usecases.appentry.ReadAppEntry
import com.example.nixfit.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAppEntry : ReadAppEntry,
): ViewModel() {
    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Screen.AppStart.route)
    val startDestination: State<String> = _startDestination
    init{
        readAppEntry().onEach {startLocation ->
            if (startLocation){
                _startDestination.value = Screen.AppMain.route
            }
            else{
                _startDestination.value = Screen.AppStart.route
            }
            delay(300)
                _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}