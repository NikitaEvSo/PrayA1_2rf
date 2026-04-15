package com.example.praya1.models

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import com.example.praya1.data.Account
import com.example.praya1.R
import com.example.praya1.data.CartData
import com.example.praya1.data.ProductData

enum class Screens {
    Registration, SingIn, Catalog, Details, Cart, Profile
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //android viewmodel \sharedPrefs
    var cart = listOf<CartData>()
    private val sharedPrefs: SharedPreferences =
        application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val productData = listOf(
        ProductData(
            images = listOf(R.drawable.image1,R.drawable.image1),
            name = "Собака",
            desc = "Игривая собака: любит прогулки и игры на свежем воздухе.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Кот",
            desc = "Нежный кот: любит ласку и спокойные вечера.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Собака",
            desc = "Верный друг: отлично подходит для активных семей.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Кот",
            desc = "Любопытный кот: обожает исследовать новые уголки дома.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Кот",
            desc = "Игривый котёнок: полон энергии и радости.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Кот",
            desc = "Спокойный взрослый кот: идеально для спокойной компании.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Собака",
            desc = "Щенок в радость: обучаемый и дружелюбный.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Кот",
            desc = "Элегантный кот: любит внимание и ухоженную среду.",
            price = 1000
        ), ProductData(
            images = listOf(R.drawable.image1),
            name = "Собака",
            desc = "Активная собака: идеальна для пробежек и игр на природе.",
            price = 1000
        )
    )
    var selectedProduct: ProductData? by mutableStateOf(null)

    fun sumOfCart(): Int {
        var summary = 0
        cart.forEach { summary += it.count.value * it.productData.price }
        return summary
    }

    fun cartNotContainItem(): Boolean {
        return cart.find { it.productData.name == selectedProduct?.name } == null
    }

    var screen by mutableStateOf(Screens.SingIn)

    var account by mutableStateOf<Account?>(null)
    fun resetAccount() {
        account = null
        sharedPrefs.edit { clear() } // Also clear the saved data
    }

    fun addToCart() {
        cart += CartData(
            selectedProduct!!, mutableIntStateOf(1)
        )
    }

    fun deleteCartItem(rmProd: ProductData) {
        cart = cart.filter { it.productData.name != rmProd.name }
    }

    fun selectNew(sel: ProductData) {
        selectedProduct = sel
    }


    fun navigateTo(screen: Screens) {
        this.screen = screen
    }

    fun saveAccount(newAccount: Account) {
        this.account = newAccount
        sharedPrefs.edit {
            putString("Name", newAccount.name)
            putString("Email", newAccount.email)
            putString("Password", newAccount.password)
        }
    }

    private fun getAccountP(): Account? {
        val name = sharedPrefs.getString("Name", null)
        val email = sharedPrefs.getString("Email", null)
        val password = sharedPrefs.getString("Password", null)

        return if (name != null && email != null) {
            Account(name, email, password ?: "")
        } else {
            null
        }
    }

    init {
        account = getAccountP()
    }
}