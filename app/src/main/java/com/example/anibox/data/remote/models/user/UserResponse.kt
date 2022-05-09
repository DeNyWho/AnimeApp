package com.example.animeapp.data.remote.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("message")
    val message: String? = null,
)