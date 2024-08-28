package com.example.dapurgampilapp.data

data class User(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val role: Int = 0,
    val password: String = "" // Tambahkan jika perlu, tetapi pertimbangkan keamanan
)