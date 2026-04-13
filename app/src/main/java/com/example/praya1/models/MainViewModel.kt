package com.example.praya1.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praya1.data.Account
import com.example.praya1.R
import com.example.praya1.data.CartData
import com.example.praya1.data.ProductData

enum class Screens {
    Registration, SingIn, Catalog, Details, Cart, Profile
}

class MainViewModel : ViewModel() {
    var cart = listOf<CartData>()
    val productData = listOf(

        ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "first",
            desc = "first product",
            1000
        ), ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "Second",
            desc = "Second product",
            1000
        ), ProductData(
            listOf(R.drawable.ic_launcher_background),
            name = "Third",
            desc = "Third product",
            1000
        )
    )
    var selectedProduct: ProductData? by mutableStateOf(null)

    fun sumOfCart(): Int{
        var summary =0
        cart.forEach {summary  += it.count.value * it.productData.price }
        return summary
    }
    fun cartNotContainItem(): Boolean{
        return cart.find { it.productData.name == selectedProduct?.name } == null
    }

    var screen by mutableStateOf(Screens.Registration)

    var account by mutableStateOf<Account?>(null)
    fun resetAccount() { account = null }
    fun addToCart(){
        cart += CartData(
            selectedProduct!!, mutableIntStateOf(1)
        )
    }

    fun deleteCartItem(rmProd: ProductData){
        cart = cart.filter { it.productData.name != rmProd.name }
    }

    fun selectNew(sel: ProductData) {selectedProduct = sel }


    fun navigateTo(screen: Screens) {
        this.screen = screen
    }

    fun saveAccount(newAccount : Account) { account = newAccount }


}