package com.example.animeapp.data.remote.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("data")
    val data: UserDto? = null,
)