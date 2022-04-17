package com.example.animeapp.data.repository

import com.example.animeapp.data.remote.models.User
import com.example.animeapp.util.Result

interface AnimeRepo {

    suspend fun createUser(user: User): Result<String>
    suspend fun login(user: User): Result<String>
    suspend fun getUserSqlInfo(email: String): User
    suspend fun getUser(): Result<User>
    suspend fun logout(): Result<String>

}