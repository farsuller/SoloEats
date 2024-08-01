package com.solodemo.main.presentations.dashboard.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.supabase.domain.repository.Carts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun CartScreen(
    paddingValues: PaddingValues,
    carts: Carts,
    cartViewModel: CartViewModel,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
    navigateToPlaceOrderSuccess: () -> Unit,
) {
    CartContent(
        carts = carts,
        paddingValues = paddingValues,
        cartViewModel = cartViewModel,
        onSuccess = onSuccess,
        onError = onError,
        navigateToPlaceOrderSuccess = navigateToPlaceOrderSuccess,
    )
}
