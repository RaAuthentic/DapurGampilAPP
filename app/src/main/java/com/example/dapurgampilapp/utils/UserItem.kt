package com.example.dapurgampilapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.data.User

@Composable
fun UserItem(
    user: User,
    onEditClick: (User) -> Unit,
    onDeleteClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Mengganti fillMaxSize dengan fillMaxWidth
            .padding(8.dp)
            .background(Color(0Xff2196F3)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Username: ${user.username}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Email: ${user.email}", fontSize = 16.sp)
        Text(text = "Role: ${user.role}", fontSize = 16.sp)

        Button(onClick = { onEditClick(user) }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit User")
            Text(text = "Edit")
        }

        Button(onClick = { onDeleteClick(user.uid) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete User")
            Text(text = "Delete")
        }
    }
}
