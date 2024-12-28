package com.example.smart_cart.data.repository

import android.util.Log
import com.example.smart_cart.data.api.ApiService
import com.example.smart_cart.data.api.ApiResponse
import com.example.smart_cart.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val apiService: ApiService

    // Initializing Retrofit instance for API Service
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://backend-153569340026.us-central1.run.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    // StateFlow for registration state
    private val _registrationState = MutableStateFlow<RegistrationResponse?>(null)
    val registrationState: StateFlow<RegistrationResponse?> = _registrationState

    // StateFlow for login state
    private val _loginState = MutableStateFlow<ApiResponse<UserData>?>(null)
    val loginState: StateFlow<ApiResponse<UserData>?> = _loginState

    // Map UserData to RegistrationData
    private fun mapUserDataToRegistrationData(userData: UserData): RegistrationData {
        return RegistrationData(
            id = userData.id,
            email = userData.email,
            name = userData.name,
            phone = userData.phone,
            fcmToken = userData.fcmToken,
            updatedAt = userData.updatedAt,
            createdAt = userData.createdAt
        )
    }

    // Map ApiResponse to RegistrationResponse
    private fun mapApiResponseToRegistrationResponse(response: ApiResponse<UserData>): RegistrationResponse {
        return RegistrationResponse(
            reason = response.reason,
            stack = response.stack,
            data = response.data?.let { mapUserDataToRegistrationData(it) }
        )
    }

    // Registration function
    suspend fun registerUser(user: RegistrationRequest, authToken: String) {
        try {
            Log.d("UserRepository", "Making API call to register user: $user")

            // Validate authToken
            if (authToken.isBlank()) {
                throw IllegalArgumentException("Authorization token cannot be empty")
            }

            // Making API call to register the user
            val response = apiService.registerUser(
                registrationRequest = user,
                authorization = "Bearer $authToken"
            )

            // Map ApiResponse to RegistrationResponse
            val mappedResponse = mapApiResponseToRegistrationResponse(response)

            // Update state based on response
            if (mappedResponse.data != null) {
                Log.d("UserRepository", "Registration successful: ${mappedResponse.data}")
                _registrationState.value = mappedResponse
            } else {
                Log.e("UserRepository", "Registration failed with reason: ${mappedResponse.reason}")
                _registrationState.value = mappedResponse
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error during registration: ${e.message}", e)
            _registrationState.value = RegistrationResponse(
                reason = "Error during registration: ${e.message}",
                stack = e.stackTraceToString()
            )
        }
    }

    // Login function
    suspend fun loginUser(email: String, password: String, authToken: String) {
        try {
            Log.d("UserRepository", "Making API call to log in user with email: $email")

            // Validate authToken
            if (authToken.isBlank()) {
                throw IllegalArgumentException("Authorization token cannot be empty")
            }

            // Creating login request
            val loginRequest = LoginRequest(email = email, password = password)

            // Making API call to log in the user
            val response = apiService.loginUser(
                loginRequest = loginRequest,
                authorization = "Bearer $authToken" // Pass the authorization token
            )

            Log.d("UserRepository", "API response: $response")

            // Update state based on response
            if (response.data != null) {
                Log.d("UserRepository", "Login successful: ${response.data}")
                _loginState.value = response
            } else {
                Log.e("UserRepository", "Login failed with reason: ${response.reason}")
                _loginState.value = response
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error during login: ${e.message}", e)
            _loginState.value = ApiResponse(
                data = null,
                reason = "Error during login: ${e.message}",
                stack = e.stackTraceToString()
            )
        }
    }
}
