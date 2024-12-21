package org.example.smart.cart.thapar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform