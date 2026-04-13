package com.example.praya1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.praya1.data.CartData
import com.example.praya1.models.MainViewModel

@Composable
fun CartRow(
    item: CartData, index: Int, model: MainViewModel
) {

    Row(
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.Companion
            .fillMaxWidth()
            .height(55.dp).background(Color(255, 255, 255, 255))
    ) {
        Image(
            painter = painterResource(item.productData.images[0]),
            contentDescription = null,
            modifier = Modifier.padding(2.dp)
        )
        Column() {
            Text("Товар${index}")
            Text(item.productData.price.toString())
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(shape = CircleShape,onClick = {
                if (item.count.value == 1) {
                    model.deleteCartItem(item.productData)
                } else {
                    item.count.value--
                }
            }) { Text("-") }
            Text("${item.count.value}шт")
            Button(shape = CircleShape, onClick = { item.count.value++ }) { Text("+") }
        }
    }
}