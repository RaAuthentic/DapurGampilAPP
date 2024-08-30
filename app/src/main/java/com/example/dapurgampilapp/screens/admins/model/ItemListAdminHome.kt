// ItemListAdminHome.kt
package com.example.dapurgampilapp.screens.admins.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.dapurgampilapp.R
import com.example.dapurgampilapp.data.ItemsHomeAdmin


@Composable
fun ItemListAdminHome() {
    val items = listOf(
        ItemsHomeAdmin("Quick Learn C# Language", "Jarmie Young", 128, 4.6, R.drawable.pic1),
        ItemsHomeAdmin("Full Course Android Kotlin", "Alex Alba", 368, 4.2, R.drawable.pic2),
        ItemsHomeAdmin("Quick Learn C# Language", "Jarmie Young", 128, 4.6, R.drawable.pic1)
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .shadow(3.dp)
                    .clickable { /* Handle click */ }
                    .padding(8.dp)
            ) {
                Column {
                    Image(
                        painter = painterResource(id = item.picUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = item.title,
                        modifier = Modifier
                            .background(Color(android.graphics.Color.parseColor("#90000008")))
                            .fillMaxWidth()
                            .padding(6.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Text(
                        text = "$${item.price}",
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#521c98")),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Row(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = item.score.toString(),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}