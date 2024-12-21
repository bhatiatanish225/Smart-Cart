package org.example.smart.cart.thapar

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Smart-Cart",
    ) {
        App()
    }
}