// PopularCourseAdminHome.kt
package com.example.dapurgampilapp.screens.admins.model

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PopularCourseAdminHome(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

        Text(
            text = "Popular",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "See all",
            fontWeight = FontWeight.SemiBold,
            color = Color(android.graphics.Color.parseColor("#521c98")),
            fontSize = 16.sp
        )
    }
}
