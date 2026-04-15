package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praya1.components.CusButton
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailsScreen(model: MainViewModel) {
    Scaffold(
        bottomBar = {
            BottomAppBar {

                CusButton( modifier = Modifier.fillMaxWidth(),
                    text = if (model.cartNotContainItem()) "Добавить в корзину" else {
                        "Уже в корзине"
                    }, onClick = {
                        model.navigateTo(Screens.Catalog)
                        if (model.cartNotContainItem()) {
                            model.addToCart()
                        }
                    }
                )
            }
        }) { it ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
        ) {
            Box() {

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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.align(alignment = Alignment.BottomCenter)
                    ) {
                        for (i in 0 until pageState.pageCount) {
                            Box(
                                Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pageState.currentPage == i) Color(0, 0, 0, 255)
                                        else Color(
                                            253, 253, 253, 255
                                        )
                                    )
                            )
                        }
                    }
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.LightGray),
                    modifier = Modifier.align(
                        Alignment.TopStart
                    ),
                    onClick = { model.navigateTo(Screens.Catalog) }) {
                    Icon(Icons.Default.ArrowBackIosNew, null)
                }
            }

            Column(
                Modifier.fillMaxHeight()
                    .background(Color(211, 208, 208, 255)).padding(8.dp)
            ) {
                Text(model.selectedProduct!!.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    model.selectedProduct!!.price.toString() + " ТГ", color = Color(
                        114, 114, 114, 255
                    )
                )

                Column(
                    Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20))
                        .background(Color(252, 252, 252, 255))
                        .padding(10.dp)
                ) {
                    Text("Описание", fontWeight = FontWeight.Medium)
                    Text(model.selectedProduct!!.desc)
                }
            }
        }
    }
}