package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praya1.components.BottomBar
import com.example.praya1.data.ProductData
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(model: MainViewModel) {
    val searchState = rememberTextFieldState()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            OutlinedTextField(
                state = searchState,
                leadingIcon = {
                    Icon(Icons.Default.Search, null)
                },
                placeholder = { Text("Поиск", fontSize = 15.sp) },
                modifier = Modifier
                    .fillMaxWidth(0.96f)
                    .height(49.dp),
                shape = RoundedCornerShape(14),
                textStyle = TextStyle(
                    fontSize = 15.sp,
                )
            )
        })
    }, bottomBar = { BottomBar(onNavigate = { model.navigateTo(it) }, model = model) }

    ) { it ->
        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(it)
                .padding(start = 12.dp, end = 12.dp, top = 3.dp)
        ) {


            items(model.productData.filter {
                it.name.contains(
                    searchState.text.toString(), ignoreCase = true
                )
            }) { p: ProductData ->

                val product = p
                CatCard(product, model)
            }
        }
    }
}

@Composable
private fun CatCard(
    product: ProductData,
    model: MainViewModel
) {
    Card(
        modifier = Modifier.Companion.padding(horizontal = 4.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, contentColor = Color.Black
        )
    ) {
        val pageState = rememberPagerState { product.images.size }
        Column(
            Modifier.Companion.clickable(onClick = {
                model.selectNew(product)
                model.navigateTo(Screens.Details)
            })
        ) {
            Pager(pageState, product)
            Column(Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                Text(
                    product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    product.desc,
                    fontSize = 15.sp,
                    color = Color(110, 110, 110, 255),
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun Pager(
    pageState: PagerState,
    product: ProductData
) {
    Box() {
        HorizontalPager(
            state = pageState
        ) { page ->

            Image(
                painter = painterResource(id = product.images[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop
            )
        }
        if (pageState.pageCount > 1) {
            PagerDots(
                pageState, Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun BoxScope.PagerDots(pageState: PagerState, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(
                Color(
                    203, 203, 203, 185
                )
            )
            .padding(horizontal = 3.dp, vertical = 3.dp)

    ) {
        if (pageState.pageCount > 1)
//            for (i in 0 until pageState.pageCount)
            repeat(pageState.pageCount) { count ->
                val color =
                    if (count == pageState.currentPage)
                        Color(0, 0, 0)
                    else
                        Color(250, 250, 250, 255)

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
    }
}