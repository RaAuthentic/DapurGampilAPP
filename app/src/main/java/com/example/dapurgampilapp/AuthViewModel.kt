package com.example.dapurgampilapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val user = auth.currentUser
        if (user == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            firestore.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    val role = document.getLong("role")?.toInt() ?: 2 // Default to Customer if role not found
                    _authState.value = AuthState.Authenticated(role)
                }
                .addOnFailureListener {
                    _authState.value = AuthState.Error("Failed to retrieve user data")
                }
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    checkAuthStatus() // Fetch user role after successful login
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, password: String, username: String, role: String) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            _authState.value = AuthState.Error("Email, password, or username can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    // Update user profile
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            // Store additional user data in Firestore
                            val userId = user.uid
                            val roleLevel = when (role) {
                                "Admin" -> 0
                                "Partner" -> 1
                                "Customer" -> 2
                                else -> -1
                            }
                            val userData = mapOf(
                                "email" to email,
                                "username" to username,
                                "role" to roleLevel
                            )

                            firestore.collection("users").document(userId)
                                .set(userData)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        _authState.value = AuthState.Authenticated(roleLevel)
                                    } else {
                                        _authState.value = AuthState.Error("Failed to save user data in Firestore")
                                    }
                                }
                        } else {
                            _authState.value = AuthState.Error("Failed to update profile")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}


sealed class AuthState {
    data class Authenticated(val role: Int) : AuthState() // Include role as an integer
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
