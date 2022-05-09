package com.example.anibox.core

object Endpoints {

    const val BASE_URL = "https://api.jikan.moe/v3/"

    private const val USER_HOST = "http://10.0.2.2:8080"
    const val USER_LOGIN = "$USER_HOST/users/login"
    const val USER_SIGN = "$USER_HOST/users/registration"
    const val USER_INFO = "$USER_HOST/users/userInfo"

    const val ANIME_TOP = "/top/anime"
    const val ANIME_DETAILS = "/anime"
    const val ANIME_SEARCH = "/search/anime"
    const val ANIME_SCHEDULES = "/schedules"

    const val MANGA_SEARCH = "/search/manga"
    const val MANGA_DETAILS = "/manga"

    const val HOST_V3 = "api.jikan.moe/v3"
    const val HOST_V4 = "api.jikan.moe/v4"
}