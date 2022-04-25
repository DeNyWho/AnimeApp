package com.example.animeapp.presentation.screens.account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.data.remote.models.user.UserLoginDto
import com.example.animeapp.domain.use_cases.login.LoginUseCase
import com.example.animeapp.domain.use_cases.login.SignUseCase
import com.example.animeapp.presentation.screens.account.state.SignUpState
import com.example.animeapp.presentation.screens.account.state.UserState
import com.example.animeapp.util.Constants.MAXIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Constants.MINIMUM_PASSWORD_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSignUseCase: SignUseCase,
    private val getLoginUseCase: LoginUseCase
): ViewModel() {

    private val _loginState : MutableSharedFlow<UserState> = MutableSharedFlow()
    val loginState: MutableSharedFlow<UserState> = _loginState

    private val _signState: MutableSharedFlow<SignUpState> = MutableSharedFlow()
    val signState: MutableSharedFlow<SignUpState> = _signState

    fun getUserLogin(user: UserLoginDto) {
        viewModelScope.launch {
            if (user.email.isEmpty() || user.password.isEmpty()) {
                _loginState.emit(UserState(message = "Some fields are empty"))
            } else if (!isEmailValid(email = user.email)) {
                _loginState.emit(UserState(message = "Incorrect email"))
            } else {
                _loginState.emit(getLoginUseCase(user = user))
            }
        }
    }

    fun getUserSignUp(user: UserDto) {
        viewModelScope.launch {
            if(user.email.isEmpty() || user.password.isEmpty() || user.name.isEmpty()) {
                _signState.emit(SignUpState(message = "Some fields are empty"))
            } else if (!isEmailValid(email = user.email)) {
                _signState.emit(SignUpState(message = "Incorrect email"))
            } else {
                _signState.emit(getSignUseCase(user = user))
            }
        }
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