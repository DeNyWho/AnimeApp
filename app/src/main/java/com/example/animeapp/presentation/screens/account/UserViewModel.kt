package com.example.animeapp.presentation.screens.account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.domain.use_cases.UseCases
import com.example.animeapp.domain.use_cases.login.LoginUseCase
import com.example.animeapp.domain.use_cases.login.SignUseCase
import com.example.animeapp.presentation.screens.account.state.UserState
import com.example.animeapp.util.Constants.MAXIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Constants.MINIMUM_PASSWORD_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSignUseCase: SignUseCase,
    private val getLoginUseCase: LoginUseCase,
    private val useCases: UseCases
): ViewModel() {

    private val _loginState : MutableState<UserState> = mutableStateOf(UserState())
    val loginState: State<UserState> = _loginState

    private val _signState: MutableState<UserState> = mutableStateOf(UserState())
    val signState: State<UserState> = _signState

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            _loginState.value =
//                getLoginUseCase.invoke()
//        }
//    }

    fun getUserLogin(user: UserLoginDto) {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            _loginState.value = _loginState.value.copy(message = "Some fields are empty")
        }

        else if(!isEmailValid(email = user.email)) {
            _loginState.value = _loginState.value.copy(message = "Some fields are empty")
        }
        else {
            getLoginUseCase(user = user).onEach {
                Timber.d(it.error.peekContent())
                _loginState.value = _loginState.value.copy(
                        data = it.data,
                        result = it.result,
                        message = it.message,
                        isLoading = it.isLoading,
                        error = it.error
                )
            }.launchIn(viewModelScope)
            Timber.d(_loginState.value.result.toString())
        }
    }

    fun getUserSignUp(user: UserDto) {
        getSignUseCase(user = user).onEach {
            Timber.d(it.error.peekContent())
            _signState.value = it
        }.launchIn(viewModelScope)
    }

    private fun isEmailValid(email: String):Boolean {
        val regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        val pattern = Pattern.compile(regex)
        return (email.isNotEmpty() && pattern.matcher(email).matches())
    }

    private fun isPasswordValid(password: String):Boolean {

        return (password.length in MINIMUM_PASSWORD_LENGTH..MAXIMUM_PASSWORD_LENGTH)

    }

}