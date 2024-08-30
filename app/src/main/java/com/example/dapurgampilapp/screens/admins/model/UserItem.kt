package com.example.dapurgampilapp.screens.admins.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.data.User

@Composable
fun UserItem(
    user: User,
    onEditClick: (User) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF8E1)) // Warna coklat muda krem
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Username: ${user.username}", fontSize = 16.sp)
            Text(text = "Email: ${user.email}", fontSize = 16.sp)
            Text(text = "Role: ${user.role}", fontSize = 16.sp)
        }
        IconButton(onClick = { onEditClick(user) }) {
            Icon(Icons.Filled.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = { onDeleteClick(user.uid) }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}
