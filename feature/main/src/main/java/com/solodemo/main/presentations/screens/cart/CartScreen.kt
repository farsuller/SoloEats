package com.solodemo.main.presentations.screens.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.solo.components.contents.EmptyContent
import com.solo.components.state.RequestState
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.domain.repository.Users

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    paddingValues: PaddingValues,
    carts: Carts,
    users: Users
) {
    val userName = remember { mutableStateOf("Empty Name") }
    when (users) {
        RequestState.Loading -> {}
        is RequestState.Success -> {
            userName.value = users.data.name
        }

        is RequestState.Error -> {}
        else -> {}
    }

    when (carts) {
        is RequestState.Loading -> {}
        is RequestState.Success -> {
            if (carts.data.isEmpty()) {
                EmptyContent(title = "Cart is Empty", "Select Product Now")
            } else {
                CartContent(
                    cartList = carts.data,
                    paddingValues = paddingValues,
                    userName = userName.value
                )
            }

        }

        is RequestState.Error -> {}
        else -> {}
    }


}
