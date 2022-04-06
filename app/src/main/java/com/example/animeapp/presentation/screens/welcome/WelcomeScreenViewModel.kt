package com.example.animeapp.presentation.screens.welcome


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class WelcomeScreenViewModel @Inject constructor(
//    private val useCases: UseCases
//): ViewModel() {
//
//    private val _onBoardingCompleted = MutableStateFlow(false)
//    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted
//
//    init {
//        viewModelScope.launch (Dispatchers.IO) {
//            _onBoardingCompleted.value =
//                useCases
//        }
//    }
//}
