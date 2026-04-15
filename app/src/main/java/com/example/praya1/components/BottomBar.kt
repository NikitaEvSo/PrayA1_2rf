package com.example.praya1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
fun BottomBar(onNavigate: (Screens) -> Unit, model: MainViewModel) {
    val current = model.screen.name
    BottomAppBar {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {

            TextButton(onClick = { onNavigate(Screens.Catalog) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val curColor: Color = if (current == Screens.Catalog.name) Color(
                        103, 105, 197, 255
                    ) else Color(0, 0, 0)
                    Icon(
                        Icons.Default.LocalFlorist, null, tint = curColor
                    )
                    Text(
                        "Каталог", color = curColor
                    )
                }
            }

            TextButton(onClick = { onNavigate(Screens.Cart) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val curColor: Color = if (current == Screens.Cart.name) Color(
                        103, 105, 197, 255
                    ) else Color(0, 0, 0)
                    Icon(
                        Icons.Default.ShoppingBag, null, tint = curColor
                    )
                    Text(
                        "Корзина", color = curColor
                    )
                }
            }

            TextButton(onClick = { onNavigate(Screens.Profile) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val curColor: Color = if (current == Screens.Profile.name) Color(
                        103, 105, 197, 255
                    ) else Color(0, 0, 0)
                    Icon(Icons.Default.AccountCircle, null, tint = curColor)
                    Text(
                        "Профиль", color = curColor
                    )
                }
            }

        }
    }
}