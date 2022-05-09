package com.example.anibox.presenter.splash


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anibox.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    private val _onLogin = MutableStateFlow(false)
    val onLogin: StateFlow<Boolean> = _onLogin

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
        viewModelScope.launch(Dispatchers.IO) {
            _onLogin.value =
                useCases.readOnLoginUseCase().stateIn(viewModelScope).value
        }
    }
}