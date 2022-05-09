package com.example.anibox.domain.use_cases

import com.example.anibox.domain.use_cases.login.read_onlogin.ReadOnLoginUseCase
import com.example.animeapp.domain.use_cases.login.save_onlogin.SaveOnLoginUseCase
import com.example.anibox.domain.use_cases.popularAnime.AnimePopularUseCase
import com.example.anibox.domain.use_cases.seasons.*
import com.example.anibox.domain.use_cases.splash.read_onboarding.ReadOnBoardingUseCase
import com.example.anibox.domain.use_cases.splash.save_onboarding.SaveOnBoardingUseCase

data class UseCases (
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnLoginUseCase: SaveOnLoginUseCase,
    val readOnLoginUseCase: ReadOnLoginUseCase,
    val animePopularUseCase: AnimePopularUseCase,
    val animeSeasonsUseCase: AnimeSeasonsUseCase,
    val animeAutumnUseCase: AnimeAutumnUseCase,
    val animeSpringUseCase: AnimeSpringUseCase,
    val animeSummerUseCase: AnimeSummerUseCase,
    val animeWinterUseCase: AnimeWinterUseCase
)