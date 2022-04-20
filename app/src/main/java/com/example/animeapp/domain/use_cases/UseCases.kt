package com.example.animeapp.domain.use_cases

import com.example.animeapp.domain.use_cases.login.read_onlogin.ReadOnLoginUseCase
import com.example.animeapp.domain.use_cases.login.save_onlogin.SaveOnLoginUseCase
import com.example.animeapp.domain.use_cases.splash.read_onboarding.ReadOnBoardingUseCase
import com.example.animeapp.domain.use_cases.splash.save_onboarding.SaveOnBoardingUseCase

data class UseCases (
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnLoginUseCase: SaveOnLoginUseCase,
    val readOnLoginUseCase: ReadOnLoginUseCase
)