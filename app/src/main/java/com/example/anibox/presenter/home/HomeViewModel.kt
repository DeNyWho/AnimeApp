package com.example.anibox.presenter.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anibox.domain.use_cases.UseCases
import com.example.anibox.presenter.home.state.popular.AnimePopularState
import com.example.anibox.presenter.home.state.seasons.AnimeAutumnState
import com.example.anibox.presenter.home.state.seasons.AnimeSpringState
import com.example.anibox.presenter.home.state.seasons.AnimeSummerState
import com.example.anibox.presenter.home.state.seasons.AnimeWinterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val UseCases: UseCases
): ViewModel() {

    private val _animeSeasonSpring: MutableState<AnimeSpringState> =
        mutableStateOf(AnimeSpringState())
    val animeSeasonSpring: MutableState<AnimeSpringState> = _animeSeasonSpring

    private val _animeSeasonSummer: MutableState<AnimeSummerState> =
        mutableStateOf(AnimeSummerState())
    val animeSeasonSummer: MutableState<AnimeSummerState> = _animeSeasonSummer

    private val _animeSeasonWinter: MutableState<AnimeWinterState> =
        mutableStateOf(AnimeWinterState())
    val animeSeasonWinter: MutableState<AnimeWinterState> = _animeSeasonWinter

    private val _animeSeasonAutumn: MutableState<AnimeAutumnState> =
        mutableStateOf(AnimeAutumnState())
    val animeSeasonAutumn: MutableState<AnimeAutumnState> = _animeSeasonAutumn

    private val _animeAiringPopular : MutableState<AnimePopularState> =
        mutableStateOf(AnimePopularState())
    val animeAiringPopular : MutableState<AnimePopularState> = _animeAiringPopular

    fun getAnimeWinter(year: Int,season: String){
        UseCases.animeWinterUseCase(year,season).onEach {
            _animeSeasonWinter.value = it
        }.launchIn(viewModelScope)
    }

    fun getAnimeSpring(year: Int,season: String){
        UseCases.animeSpringUseCase(year, season).onEach {
            _animeSeasonSpring.value = it
        }.launchIn(viewModelScope)
    }

    fun getAnimeSummer(year: Int,season: String){
        UseCases.animeSummerUseCase(year, season).onEach {
            _animeSeasonSummer.value = it
        }.launchIn(viewModelScope)
    }

    fun getAnimeAutumn(year: Int,season: String){
        UseCases.animeAutumnUseCase(year, season).onEach {
            _animeSeasonAutumn.value = it
        }.launchIn(viewModelScope)
    }

//    fun getAnimeSeason(year: Int, season: String) {
//        when (season){
//            "winter" -> UseCases.animeSeasonsUseCase.winter(year,season).onEach {
//                _animeSeasonWinter.value = it
//            }.launchIn(viewModelScope)
//            "spring" -> UseCases.animeSeasonsUseCase.spring(year, season).onEach {
//                _animeSeasonSpring.value = it
//            }.launchIn(viewModelScope)
//            "summer" -> UseCases.animeSeasonsUseCase.summer(year, season).onEach {
//                _animeSeasonSummer.value = it
//            }.launchIn(viewModelScope)
//            "autumn" -> UseCases.animeSeasonsUseCase.autumn(year, season).onEach {
//                _animeSeasonAutumn.value = it
//            }.launchIn(viewModelScope)
//        }
////        UseCases.animeSeasonsUseCase(year, season).onEach {
////            _animeSeason.value = it
////        }.launchIn(viewModelScope)
//    }

    fun getAnimeAiringPopular() {
        UseCases.animePopularUseCase().onEach {
            _animeAiringPopular.value = it
        }.launchIn(viewModelScope)
    }
}