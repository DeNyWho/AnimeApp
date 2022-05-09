package com.example.animeapp.data.remote.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginDto(
    @SerialName("email")
    val email: String = "",
    @SerialName("password")
    val password: String = ""
)

fun UserLoginDto.toLogin(): UserLogin {
    return UserLogin(
        email = email,
        password = password
    )
}