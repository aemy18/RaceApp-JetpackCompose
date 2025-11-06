package com.practical.task.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    @SerializedName("schedule") val schedule: List<Race>
)

@Serializable
data class Race(
    @SerializedName("raceId") val raceId: String,
    @SerializedName("circuitId") val circuitId: String,
    @SerializedName("isSprint") val isSprint: Boolean,
    @SerializedName("raceEndTime") val raceEndTime: Long,
    @SerializedName("raceName") val raceName: String,
    @SerializedName("raceStartTime") val raceStartTime: Long,
    @SerializedName("raceState") val raceState: String,
    @SerializedName("round") val round: Int,
    @SerializedName("sessions") val sessions: List<Session>,
    @SerializedName("podium") val podium: List<String> = emptyList()
)

@Serializable
data class Session(
    @SerializedName("sessionId") val sessionId: String,
    @SerializedName("sessionType") val sessionType: String,
    @SerializedName("sessionName") val sessionName: String,
    @SerializedName("startTime") val startTime: Long,
    @SerializedName("endTime") val endTime: Long,
    @SerializedName("sessionState") val sessionState: String,
    @SerializedName("_id") val id: String = ""
)
