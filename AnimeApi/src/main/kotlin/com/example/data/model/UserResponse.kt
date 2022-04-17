package com.example.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @Contextual
    val user: User
)
