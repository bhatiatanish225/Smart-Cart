package com.example.smart_cart.data.repository

import android.util.Log
import com.example.smart_cart.data.api.ApiService
import com.example.smart_cart.data.api.ApiResponse
import com.example.smart_cart.data.model.RegistrationData
import com.example.smart_cart.data.model.RegistrationRequest
import com.example.smart_cart.data.model.RegistrationResponse
import com.example.smart_cart.data.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://backend-153569340026.us-central1.run.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    private val _registrationState = MutableStateFlow<RegistrationResponse?>(null)
    val registrationState: StateFlow<RegistrationResponse?> = _registrationState

    // Map UserData to RegistrationData
    private fun mapUserDataToRegistrationData(userData: UserData): RegistrationData {
        Log.d("UserRepository", "Mapping UserData to RegistrationData: $userData")
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

    // Function to map ApiResponse to RegistrationResponse
    private fun mapApiResponseToRegistrationResponse(response: ApiResponse<UserData>): RegistrationResponse {
        Log.d("UserRepository", "Mapping ApiResponse to RegistrationResponse: $response")

        // If data is null, return a RegistrationResponse with reason and null data
        return RegistrationResponse(
            reason = response.reason,
            stack = response.stack,
            data = response.data?.let {
                mapUserDataToRegistrationData(it)
            } ?: RegistrationData(
                id = "",
                email = "",
                name = "",
                phone = "",
                fcmToken = null,
                updatedAt = "",
                createdAt = ""
            )  // Optionally return empty RegistrationData or an error object if backend expects data
        )
    }

    suspend fun registerUser(user: RegistrationRequest, authToken: String) {

            try {
                Log.d("UserRepository", "Making API call to register user: $user")

                // Making API call to register the user
                val response = apiService.registerUser(
                    registrationRequest = user,
                    authorization = authToken.toString()
                )

                Log.d("UserRepository", "API response: $response")

                // Map ApiResponse to RegistrationResponse
                val mappedResponse = mapApiResponseToRegistrationResponse(response)

                // Handle response success and failure
                if (mappedResponse.data != null) {
                    Log.d("UserRepository", "Registration successful: ${mappedResponse.data}")
                    _registrationState.value = mappedResponse // Success
                } else {
                    Log.e("UserRepository", "Registration failed with reason: ${mappedResponse.reason}")
                    _registrationState.value = mappedResponse // Error (with reason)
                }
            } catch (e: Exception) {
                Log.e("UserRepository", "Error during registration: ${e.message}")
                e.printStackTrace() // Handle the error
                _registrationState.value = RegistrationResponse(
                    reason = "Error during registration: ${e.message}",
                    stack = e.stackTraceToString()
                ) // Handle error state here
            }

    }
}
