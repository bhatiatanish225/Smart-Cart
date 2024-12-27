package com.example.smart_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import com.example.smart_cart.data.repository.AuthRepository
import com.example.smart_cart.ui.login.LoginViewModel
import com.example.smart_cart.ui.signup.RegisterViewModel
import com.example.smart_cart.ui.theme.SmartCartTheme
import com.example.smart_cart.navigation.AppNavGraph
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

            val authRepository = AuthRepository(firebaseAuth)


            val loginViewModel = LoginViewModel(authRepository)
            val registerViewModel = RegisterViewModel(authRepository)

            // Apply Theme and Use AppNavGraph for navigation
            SmartCartTheme {
                AppNavGraph(
                    loginViewModel = loginViewModel,
                    registerViewModel = registerViewModel
                )
            }
        }
    }
}
