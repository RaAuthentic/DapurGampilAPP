package com.example.dapurgampilapp.screens.admins

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialog(
    user: User?,
    onSave: (String, String, Int) -> Unit, // Updated to accept individual parameters
    onCancel: () -> Unit
) {
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var role by remember { mutableStateOf(user?.role?.toString() ?: "0") }

    AlertDialog(
        onDismissRequest = { onCancel() },
        title = {
            Text(text = if (user == null) "Add User" else "Edit User")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = role,
                    onValueChange = { role = it },
                    label = { Text("Role") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val userRole = role.toIntOrNull() ?: 0
                    onSave(username, email, userRole) // Pass parameters individually
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onCancel() }) {
                Text("Cancel")
            }
        }
    )
}
