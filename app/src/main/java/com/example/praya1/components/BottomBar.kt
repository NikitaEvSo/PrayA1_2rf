package com.example.praya1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
fun BottomBar(onNavigate: (Screens) -> Unit, model: MainViewModel) {
    val current = model.screen.name
    BottomAppBar {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                TextButton(onClick = { onNavigate(Screens.Catalog) }) {
                    Text(
                        "Каталог",
                        color =  if (current == Screens.Catalog.name) Color(0, 0, 0) else Color(
                            103,
                            105,
                            197,
                            255
                        )
                    )
                }
            }
            Column {
                TextButton(onClick = { onNavigate(Screens.Cart) }) { Text("Корзина",
                    color =  if (current == Screens.Cart.name) Color(0, 0, 0) else Color(
                        103,
                        105,
                        197,
                        255
                    )) }
            }
            Column {
                TextButton(onClick = { onNavigate(Screens.Profile) }) { Text("Профиль",
                    color =  if (current == Screens.Profile.name) Color(0, 0, 0) else Color(
                        103,
                        105,
                        197,
                        255
                    )) }

            }
        }
    }
}