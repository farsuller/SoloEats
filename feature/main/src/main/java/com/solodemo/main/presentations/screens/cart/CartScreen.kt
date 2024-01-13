package com.solodemo.main.presentations.screens.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.supabase.domain.repository.Carts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    paddingValues: PaddingValues,
    carts: Carts
) {
    CartContent(
        carts = carts,
        paddingValues = paddingValues
    )

}
