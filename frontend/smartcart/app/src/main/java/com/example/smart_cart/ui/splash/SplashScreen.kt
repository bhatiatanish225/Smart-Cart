package com.example.smart_cart.ui.splash

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_cart.R

@Composable
fun SplashScreen(navController: NavController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("auth_token", null)

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        if (token.isNullOrEmpty()) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("profile") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2382AA),
                        Color(0xFF64B5F6)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_image),
                contentDescription = "Splash Illustration",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 32.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Smart Cart App",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Your smart shopping assistant",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
