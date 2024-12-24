package com.example.smart_cart.data.repository

import com.example.smart_cart.data.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Retrofit API Interface
interface ApiService {
    @GET("dashboard")
    suspend fun getCategories(): ApiResponse
}

// Data classes for the API response
data class ApiResponse(val data: Data)
data class Data(val categories: List<Category>, val carousels: List<Any>)

// Repository
class CategoryRepository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://backend-153569340026.us-central1.run.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    suspend fun fetchCategories() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCategories()
                _categories.value = response.data.categories
            } catch (e: Exception) {
                e.printStackTrace() // Handle error (e.g., log it)
            }
        }
    }
}
