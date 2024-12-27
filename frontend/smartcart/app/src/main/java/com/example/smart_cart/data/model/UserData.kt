package com.example.smart_cart.data.model

data class UserData(
    val id: String,
    val email: String,
    val name: String,
    val phone: String,
    val fcmToken: String? = null, // FCM token for notifications, optional
    val updatedAt: String,
    val createdAt: String
)
