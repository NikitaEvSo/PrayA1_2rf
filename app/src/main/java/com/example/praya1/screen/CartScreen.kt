package com.example.praya1.screen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praya1.components.BottomBar
import com.example.praya1.components.CartRow
import com.example.praya1.components.PButton
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(model: MainViewModel) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("Корзина") }, actions = {
            if (model.cart.isNotEmpty()) IconButton(shape = CircleShape, onClick = {
                model.cart.forEach {
                    model.deleteCartItem(it.productData)
                }
            }) { Icon(Icons.Default.Delete, null) }
        })
    }, bottomBar = { BottomBar(onNavigate = { model.navigateTo(it) }, model = model) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(192, 192, 192, 255))
                .padding(it)
        ) {
            if (model.cart.isEmpty()) Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("В вашей корзине пока пусто", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("Добавльте товары из каталога")
                PButton(model, "Перейти к каталогу", Screens.Catalog)
            } else Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                LazyColumn(Modifier.fillMaxWidth()) {
                    item { Spacer(Modifier.height(18.dp)) }
                    itemsIndexed(model.cart) { index, item ->
                        Card {
                            CartRow(item, index, model)
                            Spacer(Modifier.height(6.dp))
                        }
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.fillMaxWidth(0.5f)) {
                        Text("Вся сумма", color = Color.Gray)
                        Text(
                            "${model.sumOfCart()} T",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp,
                            color = Color.Black
                        )
                    }
                    PButton(
                        model, "Оформить заказ", Screens.Cart
                    ) { model.cart.forEach { model.deleteCartItem(it.productData) } }
                }
            }
        }

    }
}


