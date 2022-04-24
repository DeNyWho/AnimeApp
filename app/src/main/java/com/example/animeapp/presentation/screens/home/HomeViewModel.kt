package com.example.animeapp.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.domain.use_cases.popularAnime.AnimePopularUseCase
import com.example.animeapp.presentation.screens.home.popular.state.AnimePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val AnimePopularUseCase: AnimePopularUseCase
): ViewModel() {

    private val _animeAiringPopular : MutableState<AnimePopularState> = mutableStateOf(AnimePopularState())
    val animeAiringPopular : State<AnimePopularState> = _animeAiringPopular

    fun getAnimePopular() {
        AnimePopularUseCase().onEach {
            _animeAiringPopular.value = it
        }.launchIn(viewModelScope)
    }
}