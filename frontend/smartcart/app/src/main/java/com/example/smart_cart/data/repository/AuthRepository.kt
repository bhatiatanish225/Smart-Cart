package com.example.smart_cart.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val firebaseAuth: FirebaseAuth
    // Uncomment this if you decide to use Firestore for additional user data storage
    // private val firestore: FirebaseFirestore
) {

    // Register a new user with email, password, and optional name
    suspend fun register(name: String, email: String, password: String): FirebaseUser? {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            // If the user is created successfully, you can save additional details in Firestore
            if (firebaseUser != null) {
                // Uncomment if Firestore is needed for user details
                // val user = UserData(uid = firebaseUser.uid, name = name, email = email)
                // firestore.collection("users").document(firebaseUser.uid).set(user).await()
            }

            firebaseUser
        } catch (e: Exception) {
            // Log or handle the exception (optional)
            e.printStackTrace()
            null
        }
    }

    // Login an existing user with email and password
    suspend fun login(email: String, password: String): FirebaseUser? {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            authResult.user
        } catch (e: Exception) {
            // Log or handle the exception (optional)
            e.printStackTrace()
            null
        }
    }

    // Retrieve the currently logged-in user
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Retrieve the ID token for the currently logged-in user
    suspend fun getIdToken(): String? {
        return try {
            firebaseAuth.currentUser?.getIdToken(true)?.await()?.token
        } catch (e: Exception) {
            // Log or handle the exception (optional)
            e.printStackTrace()
            null
        }
    }

    // Logout the currently logged-in user
    fun logout() {
        firebaseAuth.signOut()
    }
}
