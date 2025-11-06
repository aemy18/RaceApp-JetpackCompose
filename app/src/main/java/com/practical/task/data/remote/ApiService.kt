package com.practical.task.data.remote

import com.practical.task.data.model.DriverResponse
import com.practical.task.data.model.ScheduleResponse
import com.practical.task.data.remote.ApiConstants.BASE_URL
import com.practical.task.data.remote.ApiConstants.DRIVERS_ENDPOINT
import com.practical.task.data.remote.ApiConstants.SCHEDULES_ENDPOINT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ApiService @Inject constructor(private val client: HttpClient) {
    suspend fun fetchDrivers(): DriverResponse {
        return client.get(BASE_URL + DRIVERS_ENDPOINT).body()
    }

    suspend fun fetchSchedules(): ScheduleResponse{
        return client.get(BASE_URL + SCHEDULES_ENDPOINT).body()
    }
}