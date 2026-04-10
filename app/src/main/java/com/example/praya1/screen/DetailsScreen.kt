package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.praya1.components.PButton
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailsScreen(model: MainViewModel) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Button(onClick = { model.navigateTo(Screens.Catalog) }) {
                Text("<")
            }
        })
    }, bottomBar = {
        BottomAppBar {

                PButton(model,if (model.cartNotContainItem()) "Добавить в корзину" else { "Уже в корзине"
                },Screens.Catalog) {
                    if (model.cartNotContainItem()) {
                        model.addToCart()
                    }
                }
        }
    }) { it ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            val pageState = rememberPagerState { model.selectedProduct!!.images.size }
            HorizontalPager(
                pageState

            ) { page ->
                Image(
                    painter = painterResource(model.selectedProduct!!.images[page]),
                    contentDescription = null,
                    modifier = Modifier.Companion.height(200.dp)
                )
            }
            Text(model.selectedProduct!!.name)
            Text(model.selectedProduct!!.price.toString())
            Text(model.selectedProduct!!.desc)
        }
    }
}