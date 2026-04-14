package com.example.praya1.screen

import android.graphics.drawable.Icon
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praya1.components.PButton
import com.example.praya1.data.Account
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    model: MainViewModel
) {
    val emailState = rememberTextFieldState()
    val loginState = rememberTextFieldState()
    var password by remember { mutableStateOf("") }
    var isHidden by remember { mutableStateOf(true) }
    if (model.account!=null){
        model.navigateTo(Screens.Catalog)
    }
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Регистрация") },
                navigationIcon = { IconButton(onClick = { model.navigateTo(Screens.SingIn) }) { Icon(Icons.Default.ArrowBackIosNew,null )} })
        }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(255, 255, 255, 255))
                .padding(it)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(Modifier.height(20.dp))
                TextField(state = emailState, label = { Text("Почта") }, supportingText = {
                    if (emailState.text.toString().isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(
                            emailState.text.toString()
                        ).matches()
                    ) Text("Введите корректный адрес")
                })

                TextField(state = loginState, label = { Text("Логин") }, supportingText = {
                    if (!loginState.text.toString().isNotBlank()) Text("Заполните все поля ")
                })

                TextField(
                    value = password,
                    onValueChange = { sit: String -> password = sit },
                    label = { Text("Пароль") },
                    visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.Companion.None,
                    trailingIcon = {
                        Button(onClick = {
                            isHidden = !isHidden
                        }) { if (isHidden) Icon(Icons.Default.Remove,null)  else Icon(Icons.Default.RemoveRedEye,null) }
                    },
                    supportingText = { if (!password.isNotBlank()) Text("Заполните все поля ") })
            }


            PButton(
                model = model,
                text = "Создать аккаунт",
                screen = if (loginState.text.toString()
                        .isNotBlank() && password.isNotBlank() && emailState.text.toString()
                        .isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString())
                        .matches()
                ) {
                    Screens.Catalog
                } else {
                    Screens.Registration
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loginState.text.toString()
                        .isNotBlank() && password.isNotBlank() && emailState.text.toString()
                        .isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString())
                        .matches()
                ) model.saveAccount(
                    Account(
                        name = loginState.text.toString(),
                        email = emailState.text.toString(),
                        password = password,
                    ),
                )
            }
        }
    }
}