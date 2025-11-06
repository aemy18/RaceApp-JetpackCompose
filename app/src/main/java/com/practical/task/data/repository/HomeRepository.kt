package com.practical.task.data.repository

import com.practical.task.data.model.DriverResponse
import com.practical.task.data.model.ScheduleResponse
import com.practical.task.data.remote.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchDrivers(): DriverResponse = apiService.fetchDrivers()
    suspend fun fetchSchedules(): ScheduleResponse = apiService.fetchSchedules()
}