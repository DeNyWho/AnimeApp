package com.example.data.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
    val name: String
): Principal
