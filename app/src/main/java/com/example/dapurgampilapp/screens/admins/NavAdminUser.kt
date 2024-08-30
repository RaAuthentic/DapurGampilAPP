package com.example.dapurgampilapp.screens.admins

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dapurgampilapp.UserViewModel
import com.example.dapurgampilapp.data.User
import com.example.dapurgampilapp.screens.admins.model.UserItem

@Composable
fun NavAdminUser(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel(),
    navController: NavController
) {
    val userList by userViewModel.userList.collectAsState(initial = emptyList())
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var isEditMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        userViewModel.fetchUsers()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0B5)) // Changed to #FFE0B5
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Management",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    selectedUser = null
                    showDialog = true
                    isEditMode = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Warna tombol
            ) {
                Text(text = "Add User", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userList) { user ->
                UserItem(
                    user = user,
                    onEditClick = {
                        selectedUser = user
                        showDialog = true
                        isEditMode = true
                    },
                    onDeleteClick = { userId ->
                        userViewModel.deleteUser(userId)
                    }
                )
            }
        }
    }

    if (showDialog) {
        UserDialog(
            user = selectedUser,
            onSave = { username, email, role ->
                if (isEditMode) {
                    userViewModel.updateUser(
                        selectedUser!!.copy(username = username, email = email, role = role)
                    )
                } else {
                    userViewModel.addUser(email, username, role)
                }
                showDialog = false
                selectedUser = null
            },
            onCancel = {
                showDialog = false
                selectedUser = null
            }
        )
    }
}
