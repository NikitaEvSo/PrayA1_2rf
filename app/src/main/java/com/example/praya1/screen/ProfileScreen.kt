package com.example.praya1.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praya1.R
import com.example.praya1.components.BottomBar
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(model: MainViewModel) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Профиль") }) },
        bottomBar = { BottomBar(onNavigate = { model.navigateTo(it) }, model = model) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(178, 178, 178, 255))
                .padding(it)
        ) {
            Column(Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(model.account?.name ?: "", textAlign = TextAlign.Center, fontSize = 25.sp)
                    Text(model.account?.email ?: "", textAlign = TextAlign.Center, fontSize = 20.sp)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(
                                255, 255, 255, 255
                            )
                        )
                        .clickable(onClick = {
                            model.resetAccount()
                            model.navigateTo(Screens.SingIn)
                        }), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Выйти", color = Color.Red)
                }
            }
        }
    }
}