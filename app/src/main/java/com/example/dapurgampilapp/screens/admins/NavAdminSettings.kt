package com.example.dapurgampilapp.screens.admins

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import com.example.dapurgampilapp.AuthState
import com.example.dapurgampilapp.AuthViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.dapurgampilapp.R

@Composable
fun NavAdminSettings(
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

    // Get the context from the local composition
    val context = LocalContext.current

    // Define the color for the background
    val backgroundColor = Color(0xFFFFE0B5)

    // Create a scroll state
    val scrollState = rememberScrollState()

    // Use Column wrapped in verticalScroll for scrollable content
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = 56.dp) // Adjust for BottomNavigationBar height
            .verticalScroll(scrollState), // Make content scrollable
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Display profile image
        Image(
            painter = painterResource(id = R.drawable.keindra), // Replace with your image resource
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Keindra Bagas Maulana",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "NRP = 152021229",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Text(
            text = "Jurusan Informatika",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "I am currently a third-year student pursuing a Bachelor's degree in Informatic Engineering at Institut Technology National Bandung. Enthusiast in applying mathematical and statistical principles in computer science, my goal is to enhance productivity and sustainability in both work and daily life. Also wondering about opportunities for internships, student exchange programs, and collaborative projects.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button that navigates to YouTube video
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ng7O29i0CmA"))
                context.startActivity(intent)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Video Penjelasan Aplikasi")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign Out")
        }
    }
}
