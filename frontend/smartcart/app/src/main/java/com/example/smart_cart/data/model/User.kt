package com.example.smart_cart.model.data

data class User(
    val uid: String = "",         // Unique ID from Firebase
    val name: String = "",        // User's name
    val email: String = "",       // User's email
    val phone: String = "",       // User's phone number (optional)
    val profileImage: String? = null // URL for profile image (optional)
)
