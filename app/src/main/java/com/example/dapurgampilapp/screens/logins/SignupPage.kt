package com.example.dapurgampilapp.screens.logins

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.dapurgampilapp.AuthState
import com.example.dapurgampilapp.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    // State untuk input pengguna
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    // Daftar role yang tersedia
    val roles = listOf("Customer", "Partner", "Admin")
    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(roles[0]) }

    // Observasi state autentikasi
    val authState by authViewModel.authState.observeAsState()

    val context = LocalContext.current

    // Effect untuk menangani perubahan state autentikasi
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate("login") {
                // Opsional: hapus history navigasi agar pengguna tidak kembali ke halaman signup
                popUpTo("signup") { inclusive = true }
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    // Layout halaman signup
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Tambahkan padding untuk kejelasan layout
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome", fontSize = 32.sp)
        Text(text = "Create Your Account here", fontSize = 24.sp) // Ukuran font yang lebih proporsional

        Spacer(modifier = Modifier.height(24.dp)) // Jarak antar elemen

        // Input Nama
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Input Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f) // Adjust width here
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Input Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.8f) // Adjust width here
        )


        Spacer(modifier = Modifier.height(12.dp))

        // Dropdown Menu untuk Role
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            OutlinedTextField(
                value = selectedRole,
                onValueChange = { },
                label = { Text("Role") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                roles.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(text = role) },
                        onClick = {
                            selectedRole = role
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Signup
        Button(
            onClick = {
                // Validasi input sebelum melakukan signup
                when {
                    name.isBlank() -> {
                        Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    }
                    email.isBlank() -> {
                        Toast.makeText(context, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    }
                    password.isBlank() -> {
                        Toast.makeText(context, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Panggil fungsi signup dari AuthViewModel
                        authViewModel.signup(email, password, name, selectedRole)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Create Account")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tombol untuk navigasi ke halaman login
        TextButton(onClick = {
            navController.navigate("login") {
                // Opsional: hapus history navigasi agar pengguna tidak kembali ke halaman signup
                popUpTo("signup") { inclusive = true }
            }
        }) {
            Text(text = "Already Have Account? Login")
        }
    }
}
