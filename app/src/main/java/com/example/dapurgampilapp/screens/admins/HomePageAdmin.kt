package com.example.dapurgampilapp.screens.admins

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dapurgampilapp.AuthViewModel
import com.example.dapurgampilapp.UserViewModel
import com.example.dapurgampilapp.data.NavAdminItem
import com.example.dapurgampilapp.screens.NavAdminHome

@Composable
fun HomePageAdmin(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val navItemList = listOf(
        NavAdminItem("Home", Icons.Default.Home),
        NavAdminItem("User Management", Icons.Default.AccountCircle),
        NavAdminItem("Bio", Icons.Default.Settings),
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            // You can navigate to the respective screens here if needed
                        },
                        icon = {
                            BadgedBox(badge = {
//                                Badge()
                                Text(text = "" )
                            }) {
                                Icon(imageVector = navItem.icon, contentDescription = "Icon")
                            }
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier
                .padding(innerPadding) // Adjust content padding to avoid overlap
                .fillMaxSize(), // Ensure the content fills the available space
            selectedIndex = selectedIndex,
            navController = navController,
            authViewModel = authViewModel
        )
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val userViewModel: UserViewModel = viewModel()

    // Use Box to make sure ContentScreen fills its parent
    Box(modifier = modifier) {
        when (selectedIndex) {
            0 -> NavAdminHome(modifier = Modifier.fillMaxSize(), navController = navController, authViewModel = authViewModel)
            1 -> NavAdminUser(modifier = Modifier.fillMaxSize(), userViewModel = userViewModel, navController = navController)
            2 -> NavAdminSettings(modifier = Modifier.fillMaxSize(), navController = navController, authViewModel = authViewModel)
        }
    }
}

