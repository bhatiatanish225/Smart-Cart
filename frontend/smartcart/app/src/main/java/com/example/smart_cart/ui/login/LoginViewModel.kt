package com.example.smart_cart.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smart_cart.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {


    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    open val loginState: StateFlow<LoginState> get() = _loginState


    open fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Email and password cannot be empty")
            return
        }

        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val user = authRepository.login(email, password)
                if (user != null) {
                    val token = authRepository.getIdToken()
                    if (token != null) {
                        _loginState.value = LoginState.Success(token)
                    } else {
                        _loginState.value = LoginState.Error("Failed to retrieve token")
                    }
                } else {
                    _loginState.value = LoginState.Error("Invalid email or password")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }
}


sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}
