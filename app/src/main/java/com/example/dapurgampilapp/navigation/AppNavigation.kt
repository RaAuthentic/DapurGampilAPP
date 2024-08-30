package com.example.dapurgampilapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dapurgampilapp.AuthViewModel
import com.example.dapurgampilapp.screens.logins.LoginPage
import com.example.dapurgampilapp.screens.logins.SignupPage
import com.example.dapurgampilapp.screens.admins.HomePageAdmin
import com.example.dapurgampilapp.screens.customer.HomePageCustomer
import com.example.dapurgampilapp.screens.partner.HomePagePartner
import com.example.dapurgampilapp.screens.logins.HomePage

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController = navController, authViewModel = authViewModel)
        }
        composable("signup") {
            SignupPage(navController = navController, authViewModel = authViewModel)
        }
        composable("home") {
            HomePage(NavController = navController, authViewModel = authViewModel)
        }
        composable("home admin") {
            HomePageAdmin(navController = navController, authViewModel = authViewModel)
        }
        composable("home customer") {
            HomePageCustomer(navController = navController, authViewModel = authViewModel)
        }
        composable("home partner") {
            HomePagePartner(navController = navController, authViewModel = authViewModel)
        }
    }
}
