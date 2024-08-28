package com.example.dapurgampilapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapurgampilapp.data.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> = _userList

    private val db = FirebaseFirestore.getInstance()

    fun fetchUsers() {
        viewModelScope.launch {
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    val users = result.map { document ->
                        User(
                            uid = document.id,
                            username = document.getString("username") ?: "",
                            email = document.getString("email") ?: "",
                            role = (document.getLong("role") ?: 0).toInt()
                        )
                    }
                    _userList.value = users
                }
                .addOnFailureListener { exception ->
                    Log.e("UserViewModel", "Error getting users: ", exception)
                }
        }
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch {
            db.collection("users").document(userId)
                .delete()
                .addOnSuccessListener {
                    Log.d("UserViewModel", "User successfully deleted!")
                    fetchUsers() // Refresh data
                }
                .addOnFailureListener { exception ->
                    Log.e("UserViewModel", "Error deleting user: ", exception)
                }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            db.collection("users").document(user.uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d("UserViewModel", "User successfully updated!")
                    fetchUsers() // Refresh data
                }
                .addOnFailureListener { exception ->
                    Log.e("UserViewModel", "Error updating user: ", exception)
                }
        }
    }
}

