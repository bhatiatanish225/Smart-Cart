package com.example.smart_cart.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_cart.ui.home.HomeScreen
import com.example.smart_cart.ui.login.LoginScreen
import com.example.smart_cart.ui.login.LoginViewModel
import com.example.smart_cart.ui.signup.SignupScreen
import com.example.smart_cart.ui.signup.RegisterViewModel

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
                    // Navigate to Home on successful login
                    navController.navigate("home") { popUpTo("login") { inclusive = true } }
                },
                onRegisterClick = { navController.navigate("signup") },
                onForgotPasswordClick = { /* Handle forgot password */ }
            )
        }

        // Signup Screen
        composable("signup") {
            SignupScreen(
                viewModel = registerViewModel, // ViewModel is directly passed to handle logic
                onAlreadyHaveAccountClick = { navController.navigate("login") }
            )
        }
        composable("profile") {

        }
    }
}
