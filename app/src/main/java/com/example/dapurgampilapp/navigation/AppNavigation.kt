package com.example.dapurgampilapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dapurgampilapp.screens.LoginPage
import com.example.dapurgampilapp.AuthViewModel
import com.example.dapurgampilapp.screens.HomePage
import com.example.dapurgampilapp.screens.HomePageAdmin
import com.example.dapurgampilapp.screens.HomePageCustomer
import com.example.dapurgampilapp.screens.HomePagePartner
import com.example.dapurgampilapp.screens.SignupPage

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup") {
            SignupPage(modifier,navController,authViewModel)
        }
        composable("home") {
            HomePage(modifier,navController,authViewModel)
        }
        composable("home admin") {
            HomePageAdmin(modifier,navController,authViewModel)
        }
        composable("home customer") {
            HomePageCustomer(modifier,navController,authViewModel)
        }
        composable("home partner") {
            HomePagePartner(modifier,navController,authViewModel)
        }
    })
}

