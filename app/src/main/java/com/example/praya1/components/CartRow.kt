package com.example.praya1.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praya1.data.CartData
import com.example.praya1.models.MainViewModel

@Composable
fun CartRow(
    item: CartData, index: Int, model: MainViewModel
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color(255, 255, 255, 255))
    ) {
        Image(
            painter = painterResource(item.productData.images[0]),
            contentDescription = null,
            modifier = Modifier.padding(4.dp).clip(RoundedCornerShape(16))
        )
        Column() {
            Text("Товар N${index}")
            Text(item.productData.price.toString()+'Т', fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 20.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(shape = CircleShape, onClick = {
                if (item.count.value == 1) {
                    model.deleteCartItem(item.productData)
                } else {
                    item.count.value--
                }
            }) {
                if (item.count.value == 1) Icon(
                    Icons.Default.Delete, null, tint = Color(239, 41, 41, 255)
                ) else Icon(Icons.Default.Remove, null, tint = Color(89, 41, 245, 255))
            }
            Text("${item.count.value}шт", Modifier.padding(horizontal = 8.dp))
            IconButton(
                shape = CircleShape, onClick = { item.count.value++ }) {
                Icon(
                    Icons.Default.Add,
                    null, tint = Color(89, 41, 245, 255)
                )
            }
        }
    }
}