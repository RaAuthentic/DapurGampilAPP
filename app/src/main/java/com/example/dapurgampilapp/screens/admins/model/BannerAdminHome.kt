package com.example.dapurgampilapp.screens.admins.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.R

@Composable
fun BannerAdminHome() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp)
            .background(
                color = Color(android.graphics.Color.parseColor("#521c98")),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.girl2),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Selamat datang, Pilih menu kesukaanmu Hari ini",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Buy Now",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                    modifier = Modifier
                        .background(
                            color = Color(android.graphics.Color.parseColor("#521c98")),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}
