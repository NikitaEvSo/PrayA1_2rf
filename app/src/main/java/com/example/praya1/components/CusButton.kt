package com.example.praya1.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CusButton(
    modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit, text: String
) {
    Button(
        shape = RoundedCornerShape(25), enabled = enabled, colors = buttonColors(
            containerColor = Color(80, 36, 238, 255),
            contentColor = Color(255, 255, 255, 255),
        ), modifier = modifier, onClick = {
            onClick()
        }) {
        Text(text)
    }
}