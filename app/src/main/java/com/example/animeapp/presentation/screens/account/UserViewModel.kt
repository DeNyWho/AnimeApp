package com.example.animeapp.presentation.screens.account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.models.user.User
import com.example.animeapp.data.remote.models.user.UserDto
import com.example.animeapp.domain.use_cases.UseCases
import com.example.animeapp.domain.use_cases.login.LoginUseCase
import com.example.animeapp.domain.use_cases.login.SignUseCase
import com.example.animeapp.presentation.screens.account.state.UserState
import com.example.animeapp.util.Constants.MAXIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Constants.MINIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Result
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


    private val _currentUserState = MutableSharedFlow<Result<User>>()
    val currentUserState: SharedFlow<Result<User>> = _currentUserState

    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            _onLoginCompleted.value =
//                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
//        }
//    }

    fun saveOnLoginState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingUseCase(completed = completed)
        }
    }

    fun getUserLogin(user: UserDto) {
        getLoginUseCase(user = user).onEach {
            Timber.d(it.error.peekContent())
            _loginState.value = it
        }.launchIn(viewModelScope)
    }

    fun getUserSignUp(user: UserDto) {
        getSignUseCase(user = user).onEach {
            Timber.d(it.error.peekContent())
            _signState.value = it
        }.launchIn(viewModelScope)
    }


//    fun createUser(
//        name: String,
//        email: String,
//        password: String,
//        confirmPassword: String
//    ) = viewModelScope.launch {
//        _signState.value.emit
//
//        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || password != confirmPassword) {
//            _signState.emit(Result.Error("Some fields are empty"))
//            return@launch
//        }
//
//        if (!isEmailValid(email = email)) {
//            _registrationState.emit(Result.Error("Email is not valid!"))
//            return@launch
//        }
//
//        if (!isPasswordValid(password = password)) {
//            _registrationState.emit(Result.Error("Password should be between $MINIMUM_PASSWORD_LENGTH and $MAXIMUM_PASSWORD_LENGTH"))
//        }
//
//        val newUser = User(
//            userName = name,
//            email = email,
//            password = password
//        )
//        _registrationState.emit(animeRepo.createUser(newUser))
//    }
//
//    fun loginUser(
//        email: String,
//        password: String
//    ) = viewModelScope.launch {
//        _loginState.emit(Result.Loading())
//
//        if (email.isEmpty() || password.isEmpty()) {
//            _loginState.emit(Result.Error("Some fields are empty"))
//            return@launch
//        }
//
//        if (!isPasswordValid(password = password)) {
//            _loginState.emit(Result.Error("Password should be more $MINIMUM_PASSWORD_LENGTH"))
//            return@launch
//        }
//
//        val user = User(
//            email = email,
//            password = password
//        )
//        _loginState.emit(animeRepo.login(user))
//    }
//
//
//    suspend fun getUserSqlInfo(email: String): User {
//        return animeRepo.getUserSqlInfo(email = email)
//    }
//
//    private fun getCurrentUser()  = viewModelScope.launch{
//        _currentUserState.emit(Result.Loading())
//        _currentUserState.emit(animeRepo.getUser())
//    }
//
//    fun logout() = viewModelScope.launch {
//        val result = animeRepo.logout()
//        if(result is Result.Success){
//            getCurrentUser()
//        }
//    }
//
//    private fun isEmailValid(email: String):Boolean {
//        val regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
//        val pattern = Pattern.compile(regex)
//        return (email.isNotEmpty() && pattern.matcher(email).matches())
//    }
//
//    private fun isPasswordValid(password: String):Boolean {
//
//        return (password.length in MINIMUM_PASSWORD_LENGTH..MAXIMUM_PASSWORD_LENGTH)
//
//    }






































}