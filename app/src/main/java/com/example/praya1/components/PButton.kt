package com.example.praya1.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
fun PButton(
    model: MainViewModel, text: String, screen: Screens, command: () -> Unit = {}
) {
    Button(shape = RectangleShape,
        colors = buttonColors(
            containerColor = Color(130, 36, 238, 255),
            contentColor = Color(255, 255, 255, 255),
        ), onClick = {
            command()
            model.navigateTo(screen)
        }) {
        Text(text)
    }
}