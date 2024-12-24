package com.example.smart_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.smart_cart.data.repository.AuthRepository
import com.example.smart_cart.navigation.AppNavGraph
import com.example.smart_cart.ui.login.LoginViewModel
import com.example.smart_cart.ui.profile.MainScreen
import com.example.smart_cart.ui.signup.RegisterViewModel
import com.example.smart_cart.ui.theme.SmartCartTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            // Initialize Firebase instances
            val firebaseAuth = FirebaseAuth.getInstance()
            val firestore = FirebaseFirestore.getInstance()

            // Create AuthRepository
            val authRepository = AuthRepository(firebaseAuth, firestore)

            // Initialize ViewModels
            val loginViewModel = LoginViewModel(authRepository)
            val registerViewModel = RegisterViewModel(authRepository)

            // Apply Theme and Pass ViewModels to AppNavGraph
            SmartCartTheme {
//                AppNavGraph(
//                    loginViewModel = loginViewModel,
//                    registerViewModel = registerViewModel )

                MainScreen()
            }
        }
    }
}
