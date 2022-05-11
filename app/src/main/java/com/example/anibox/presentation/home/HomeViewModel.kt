package com.example.anibox.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anibox.domain.use_cases.UseCases
import com.example.anibox.presentation.home.data.AnimeTopState
import com.example.anibox.presentation.home.data.MangaTopState
import com.example.anibox.presentation.home.state.popular.AnimePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val UseCases: UseCases
): ViewModel() {

    private val _animeAiringPopular : MutableState<AnimePopularState> =
        mutableStateOf(AnimePopularState())
    val animeAiringPopular : MutableState<AnimePopularState> = _animeAiringPopular

    private val _animeTop: MutableState<AnimeTopState> =
        mutableStateOf(AnimeTopState())
    val animeTop: MutableState<AnimeTopState> = _animeTop

    private val _mangaTop: MutableState<MangaTopState> =
        mutableStateOf(MangaTopState())
    val mangaTop: MutableState<MangaTopState> = _mangaTop

    fun getMangaTop() {
        UseCases.mangaTop().onEach {
            _mangaTop.value = it
        }.launchIn(viewModelScope)
    }

    fun getAnimeTop() {
        UseCases.animeTop().onEach {
            _animeTop.value = it
        }.launchIn(viewModelScope)
    }

    fun getAnimeAiringPopular() {
        UseCases.animePopularUseCase().onEach {
            _animeAiringPopular.value = it
        }.launchIn(viewModelScope)
    }
}