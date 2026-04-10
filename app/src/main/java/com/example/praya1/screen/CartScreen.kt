package com.example.praya1.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praya1.components.BottomBar
import com.example.praya1.components.CartRow
import com.example.praya1.components.PButton
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(model: MainViewModel) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Корзина") }, actions = {
            if (model.cart.isNotEmpty()) Button(onClick = {
                model.cart.forEach {
                    model.deleteCartItem(it.productData)
                }
            }) { Text("X") }
        })
    }, bottomBar = { BottomBar(onNavigate = { model.navigateTo(it) }) }) {
        Surface(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            if (model.cart.isEmpty()) Column(
                modifier = Modifier.Companion.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Companion.CenterHorizontally
            ) {
                Text("В вашей корзине пока пусто")
                Text("Добавльте товары из каталога")
                PButton(model, "Перейти к каталогу", Screens.Catalog)
            } else Column(
                modifier = Modifier.Companion.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                LazyColumn(Modifier.Companion.fillMaxWidth()) {
                    itemsIndexed(model.cart) { index, item ->
                        Card {
                            CartRow(item, index) { model.deleteCartItem(it) }
                        }
                    }
                }
                Card {
                    Row(
                        Modifier.Companion
                            .fillMaxWidth()
                            .height(70.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(Modifier.Companion.fillMaxWidth()) {
                            Text("Вся сумма")
                            Text("${model.sumOfCart()} T")
                        }
                        PButton(
                            model, "Оформить заказ", Screens.Cart
                        ) { model.cart.forEach { model.deleteCartItem(it.productData) } }
                    }
                }
            }
        }
    }
}


