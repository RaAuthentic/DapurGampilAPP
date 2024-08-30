package com.example.dapurgampilapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dapurgampilapp.AuthState
import com.example.dapurgampilapp.AuthViewModel
import com.example.dapurgampilapp.screens.admins.model.BannerAdminHome
import com.example.dapurgampilapp.screens.admins.model.CategoriesAdminHome
import com.example.dapurgampilapp.screens.admins.model.ItemListAdminHome
import com.example.dapurgampilapp.screens.admins.model.PopularCourseAdminHome
import com.example.dapurgampilapp.screens.admins.model.SearchRowAdminHome

@Composable
fun NavAdminHome(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SearchRowAdminHome()
        }
        item {
            BannerAdminHome()
        }
        item {
            CategoriesAdminHome()
        }
        item {
            PopularCourseAdminHome()
        }
        item {
            ItemListAdminHome()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp)) // Spacer for padding
        }
        item {
            Text(
                text = "Home Page",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        item {
            TextButton(onClick = {
                authViewModel.signout()
            }) {
                Text(text = "Sign Out")
            }
        }
    }
}
