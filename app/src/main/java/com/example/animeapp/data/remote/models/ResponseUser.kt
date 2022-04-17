package com.example.animeapp.data.remote.models

data class ResponseUser(
    val success: Boolean,
    val message: String,
    val user: User
)
