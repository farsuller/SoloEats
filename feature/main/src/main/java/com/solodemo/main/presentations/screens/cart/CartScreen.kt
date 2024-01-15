package com.solodemo.main.presentations.screens.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solo.components.contents.EmptyContent
import com.solodemo.supabase.model.Cart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    paddingValues: PaddingValues,
    carts: List<Cart>,
    cartViewModel: CartViewModel
) {

    if (carts.isEmpty()) {
        EmptyContent(title = "Cart is Empty", "Select Product Now")
    } else {
        CartContent(
            cartList = carts,
            paddingValues = paddingValues,
            cartViewModel = cartViewModel
        )
    }


}
