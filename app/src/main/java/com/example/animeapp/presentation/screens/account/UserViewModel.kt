package com.example.animeapp.presentation.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.models.User
import com.example.animeapp.data.repository.AnimeRepo
import com.example.animeapp.domain.use_cases.UseCases
import com.example.animeapp.util.Constants.MAXIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Constants.MINIMUM_PASSWORD_LENGTH
import com.example.animeapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val animeRepo: AnimeRepo,
    private val useCases: UseCases
): ViewModel() {

    private val _registrationState = MutableSharedFlow<Result<String>>()
    val registrationState: SharedFlow<Result<String>> = _registrationState

    private val _loginState = MutableSharedFlow<Result<String>>()
    val loginState: SharedFlow<Result<String>> = _loginState

    private val _currentUserState = MutableSharedFlow<Result<User>>()
    val currentUserState: SharedFlow<Result<User>> = _currentUserState

    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onLoginCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }

    fun saveOnLoginState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnBoardingUseCase(completed = completed)
        }
    }


    fun createUser(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) = viewModelScope.launch {
        _registrationState.emit(Result.Loading())

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || password != confirmPassword) {
            _registrationState.emit(Result.Error("Some fields are empty"))
            return@launch
        }

        if (!isEmailValid(email = email)) {
            _registrationState.emit(Result.Error("Email is not valid!"))
            return@launch
        }

        if (!isPasswordValid(password = password)) {
            _registrationState.emit(Result.Error("Password should be between $MINIMUM_PASSWORD_LENGTH and $MAXIMUM_PASSWORD_LENGTH"))
        }

        val newUser = User(
            userName = name,
            email = email,
            password = password
        )
        _registrationState.emit(animeRepo.createUser(newUser))
    }

    fun loginUser(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginState.emit(Result.Loading())

        if (email.isEmpty() || password.isEmpty()) {
            _loginState.emit(Result.Error("Some fields are empty"))
            return@launch
        }

        if (!isPasswordValid(password = password)) {
            _loginState.emit(Result.Error("Password should be more $MINIMUM_PASSWORD_LENGTH"))
            return@launch
        }

        val user = User(
            email = email,
            password = password
        )
        _loginState.emit(animeRepo.login(user))
    }


    suspend fun getUserSqlInfo(email: String): User {
        return animeRepo.getUserSqlInfo(email = email)
    }

    private fun getCurrentUser()  = viewModelScope.launch{
        _currentUserState.emit(Result.Loading())
        _currentUserState.emit(animeRepo.getUser())
    }

    fun logout() = viewModelScope.launch {
        val result = animeRepo.logout()
        if(result is Result.Success){
            getCurrentUser()
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