package com.example.dapurgampilapp.screens.admins

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dapurgampilapp.UserViewModel
import com.example.dapurgampilapp.data.User
import com.example.dapurgampilapp.utils.UserItem

@Composable
fun NavAdminUser(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel(),
    navController: NavController
) {
    val userList by userViewModel.userList.collectAsState(initial = emptyList())
    var selectedUser by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(Unit) {
        userViewModel.fetchUsers()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User Page", fontSize = 40.sp, fontWeight = FontWeight.SemiBold)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userList) { user ->
                UserItem(
                    user = user,
                    onEditClick = { userToEdit ->
                        selectedUser = userToEdit
                    },
                    onDeleteClick = { userId ->
                        userViewModel.deleteUser(userId)
                    }
                )
            }
        }
    }

    // Tampilkan layar edit jika selectedUser tidak null
    selectedUser?.let { user ->
        EditUserScreen(
            user = user,
            onSave = { updatedUser ->
                userViewModel.updateUser(updatedUser)
                selectedUser = null // Tutup layar edit
            },
            onCancel = {
                selectedUser = null // Tutup layar edit
            }
        )
    }
}


