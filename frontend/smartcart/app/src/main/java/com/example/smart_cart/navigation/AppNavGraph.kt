package com.example.smart_cart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_cart.ui.home.HomeScreen
import com.example.smart_cart.ui.login.LoginScreen
import com.example.smart_cart.ui.login.LoginViewModel
import com.example.smart_cart.ui.profile.MainScreen
import com.example.smart_cart.ui.signup.SignupScreen
import com.example.smart_cart.ui.signup.RegisterViewModel
import com.example.smart_cart.ui.splash.SplashScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home Screen
        composable("home") {
            HomeScreen(
                onLoginClick = { navController.navigate("login") },
                onRegisterClick = { navController.navigate("signup") }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { token ->
                    // Navigate to Splash Screen first
                    navController.navigate("splash")
                },
                onRegisterClick = { navController.navigate("signup") },
                onForgotPasswordClick = {
                    // Navigate to Forgot Password screen if implemented
                    navController.navigate("forgot_password")
                }
            )
        }

        // Signup Screen
        composable("signup") {
            SignupScreen(
                viewModel = registerViewModel,
                onAlreadyHaveAccountClick = { navController.navigate("login") }
            )
        }

        // Profile Screen
        composable("profile") {
            MainScreen(
                onLogoutClick = {
                    // Navigate back to login on logout
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        // Splash Screen
        composable("splash") {
            SplashScreen(
                navController = navController,
                context = TODO()
            )
        }

        // Placeholder for Forgot Password Screen (if needed)
        composable("forgot_password") {
            // Add your ForgotPasswordScreen here
            // ForgotPasswordScreen()
        }
    }
}
