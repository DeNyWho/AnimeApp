package com.example.animeapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details: Screen("details_screen")
    object Manga: Screen("manga_screen")
    object Profile: Screen("profile_screen")
    object Anime: Screen("anime_screen")
    object RandomAnime: Screen("random_screen")
    object RandomManga: Screen("random_anime")
}