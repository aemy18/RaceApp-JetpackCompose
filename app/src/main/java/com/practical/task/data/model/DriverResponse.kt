package com.practical.task.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(
    @SerializedName("drivers")
    val drivers: List<DriverItem>
)

@Serializable
data class DriverItem(
    @SerializedName("driverId")
    val driverId: String,
    @SerializedName("podiums")
    val podiums: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("poles")
    val poles: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("teamId")
    val teamId: String,
    @SerializedName("wins")
    val wins: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("driverCode")
    val driverCode: String,
    @SerializedName("teamName")
    val teamName: String,
    @SerializedName("racingNumber")
    val racingNumber: Int,
)
