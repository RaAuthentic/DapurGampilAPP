package com.example.dapurgampilapp.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dapurgampilapp.AuthState
import com.example.dapurgampilapp.AuthViewModel
import com.example.dapurgampilapp.UserViewModel
import com.example.dapurgampilapp.data.NavAdminItem
import com.example.dapurgampilapp.screens.NavAdminHome
import com.example.dapurgampilapp.screens.partner.NavPartnerGrafik
import com.example.dapurgampilapp.screens.partner.NavPartnerTable

@Composable
fun HomePageCustomer(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val navItemList = listOf(
        NavAdminItem("Home", Icons.Default.Home),
        NavAdminItem("Menu", Icons.Default.Menu),
        NavAdminItem("Api", Icons.Default.Star),
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
            0 -> NavAdminHome(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                authViewModel = authViewModel
            )
            1 -> NavPartnerGrafik(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                authViewModel = authViewModel // Menambahkan authViewModel di sini
            )
            2 -> NavPartnerTable(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                authViewModel = authViewModel // Menambahkan authViewModel di sini
            )
        }
    }
}
