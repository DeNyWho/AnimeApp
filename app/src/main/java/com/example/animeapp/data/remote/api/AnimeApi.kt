package com.example.animeapp.data.remote.api

import com.example.animeapp.data.remote.models.SimpleResponse
import com.example.animeapp.data.remote.models.User
import retrofit2.http.*

interface AnimeApi {

    // Auth
    @Headers("Content-Type: application/json")
    @POST("/users/registration")
    suspend fun createAccount(
        @Body user: User
    ): SimpleResponse

    @Headers("Content-Type: application/json")
    @POST("/users/login")
    suspend fun login(
        @Body user:User
    ): SimpleResponse

    // Get user account from PostgreSQL
    @Headers("Content-Type: application/json")
    @GET("/users/userInfo?")
    suspend fun getUserInfo(
        @Query("email") email: String
    ): User

}