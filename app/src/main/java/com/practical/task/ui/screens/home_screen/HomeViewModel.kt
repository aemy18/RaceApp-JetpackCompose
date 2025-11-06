package com.practical.task.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practical.task.data.UiState
import com.practical.task.data.model.DriverResponse
import com.practical.task.data.model.ScheduleResponse
import com.practical.task.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<DriverResponse>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiStateSchedules = MutableStateFlow<UiState<ScheduleResponse>>(UiState.Loading)
    val uiStateSchedules = _uiStateSchedules.asStateFlow()

    init {
        fetchDrivers()
    }

    fun fetchDrivers() {
        viewModelScope.launch {
            try {
                val drivers = repository.fetchDrivers()
                _uiState.value = UiState.Success(drivers)
                fetchSchedules()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun fetchSchedules() {
        viewModelScope.launch {
            try {
                val schedules = repository.fetchSchedules()
                _uiStateSchedules.value = UiState.Success(schedules)
            } catch (e: Exception) {
                _uiStateSchedules.value = UiState.Error(e.message ?: "Something went wrong")
                println("Error : ${e.message}")
            }
        }
    }
}
