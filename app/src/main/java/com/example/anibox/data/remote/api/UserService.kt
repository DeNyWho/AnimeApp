package com.example.anibox.data.remote.api

import com.example.anibox.core.wrapper.Resource
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.data.remote.models.user.UserResponse
import com.example.animeapp.data.remote.models.user.UserSignResponse

interface UserService {

    suspend fun getLogin(user: UserLoginDto): Resource<UserResponse>

    suspend fun getSign(user: UserDto): Resource<UserSignResponse>

    suspend fun getSqlInfo(email: String): Resource<UserDto>
}