package com.example.animeapp.data.remote.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("email")
    val email: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("password")
    val password: String = ""
)

fun UserDto.toUser(): User {
    return User(
        email = email,
        password = password,
        name = name
    )
}
