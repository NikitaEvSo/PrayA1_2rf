package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
            IconButton(onClick = { model.navigateTo(Screens.Catalog) }) {
                Icon(Icons.Default.ArrowBackIosNew,null)
            }
        })
    }, bottomBar = {
        BottomAppBar {

            PButton(
                model, if (model.cartNotContainItem()) "Добавить в корзину" else {
                    "Уже в корзине"
                }, Screens.Catalog, Modifier.fillMaxWidth()
            ) {
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
            Box(contentAlignment = Alignment.Center) {
                HorizontalPager(
                    pageState, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.45f)

                ) { page ->
                    Image(
                        painter = painterResource(model.selectedProduct!!.images[page]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(124, 124, 124, 255)),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop

                    )
                }
            }
            Column(Modifier.padding(5.dp)) {
                Text(model.selectedProduct!!.name)
                Text(model.selectedProduct!!.price.toString())

                Column (Modifier.padding(4.dp)){
                    Text("Описание")
                    Text(model.selectedProduct!!.desc)
                }
            }
        }
    }
}