package com.example.praya1.screen

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.praya1.components.PButton
import com.example.praya1.data.Account
import com.example.praya1.models.MainViewModel
import com.example.praya1.models.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingInScreen(
    model: MainViewModel
) {

    Scaffold(

        topBar = { CenterAlignedTopAppBar(title = { Text("Авторизация") }) }) {

        var emailState = rememberTextFieldState()
        var loginState = rememberTextFieldState()
        var password by rememberSaveable() { mutableStateOf("") }
        var isHidden by remember { mutableStateOf(true) }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally,
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(color = Color(227, 226, 226, 255))
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            if (model.account != null) {
                model.navigateTo(Screens.Catalog)
            }
            TextField(state = emailState, label = { Text("Почта") }, supportingText = {
                if (emailState.text.toString().isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) Text("Введите корректный адрес")
            }, modifier = Modifier.fillMaxWidth())
            TextField(
                value = password,
                onValueChange = { sit: String -> password = sit },
                label = { Text("Пароль") },
                visualTransformation = if (isHidden) PasswordVisualTransformation() else VisualTransformation.Companion.None,
                supportingText = { if (!password.isNotBlank()) Text("Заполните все поля ") },
                modifier = Modifier.fillMaxWidth()
            )
            PButton(
                model,
                text = "Войти",
                screen = if (password.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) {
                    Screens.Catalog
                } else {
                    Screens.SingIn
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (password.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(
                        emailState.text.toString()
                    ).matches()
                ) {
                    model.saveAccount(
                        Account(
                            name = loginState.text.toString(),
                            email = emailState.text.toString(),
                            password = password
                        )
                    )
                }
            }
            PButton(
                model,
                "зарегистрироваться",
                Screens.Registration,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}