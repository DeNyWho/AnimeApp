package com.example.anibox.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anibox.domain.use_cases.UseCases
import com.example.anibox.presentation.detail.state.ContentDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _contentDetailsState: MutableState<ContentDetailsState> = mutableStateOf(ContentDetailsState())
    val contentDetailsState: State<ContentDetailsState> = _contentDetailsState

    private val _isRefreshing: MutableState<Boolean> = mutableStateOf(false)
    val isRefreshing: State<Boolean> = _isRefreshing

    fun getContentDetails(contentType: String?, malId: Int?, refresh: Boolean = false) {
        useCases.details(contentType, malId).onEach {
            _contentDetailsState.value = it
            if (it.isLoading) {
                _isRefreshing.value = refresh
            } else {
                _isRefreshing.value = false
            }
        }.launchIn(viewModelScope)
    }



}