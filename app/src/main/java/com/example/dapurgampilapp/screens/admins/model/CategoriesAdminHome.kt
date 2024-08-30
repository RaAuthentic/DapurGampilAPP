package com.example.dapurgampilapp.screens.admins.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
fun CategoriesAdminHome() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Category",
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

        // Category Items Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryItem(
                imageResId = R.drawable.cat1,
                categoryName = "Busines"
            )
            CategoryItem(
                imageResId = R.drawable.cat2,
                categoryName = "Creative"
            )
            CategoryItem(
                imageResId = R.drawable.cat3,
                categoryName = "Koding"
            )
            CategoryItem(
                imageResId = R.drawable.cat4,
                categoryName = "Gaming"
            )
        }
    }
}

@Composable
fun RowScope.CategoryItem(
    imageResId: Int,
    categoryName: String
) {
    Column(
        modifier = Modifier
            .weight(1f) // Membuat setiap item memiliki ukuran yang sama
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp) // Menentukan ukuran tetap untuk gambar
                .background(
                    color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
        )
        Text(
            text = categoryName,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp),
            color = Color(android.graphics.Color.parseColor("#521c98"))
        )
    }
}


