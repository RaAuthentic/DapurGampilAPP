package com.example.dapurgampilapp.screens.admins

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.data.User

@Composable
fun EditUserScreen(
    user: User,
    onSave: (User) -> Unit,
    onCancel: () -> Unit
) {
    var username by remember { mutableStateOf(user.username) }
    var email by remember { mutableStateOf(user.email) }
    var role by remember { mutableStateOf(user.role) }
    var saveConfirmation by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Edit User", fontSize = 32.sp, modifier = Modifier.padding(bottom = 16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = role.toString(),
                onValueChange = { role = it.toIntOrNull() ?: role },
                label = { Text("Role") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    onSave(user.copy(username = username, email = email, role = role))
                    saveConfirmation = true
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(text = "Save")
            }

            Button(
                onClick = { onCancel() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cancel")
            }

            if (saveConfirmation) {
                Text(
                    text = "User data saved successfully!",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}



