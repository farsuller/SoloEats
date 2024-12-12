package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.solodemo.main.model.Coupons

@Composable
fun CouponsContent() {
    var selectedCouponItem by remember { mutableStateOf<Coupons?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier,
            text = "Coupons and Promo",
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = 22.sp,
        )
        LazyRow {
            items(Coupons.entries.toTypedArray()) { couponItem ->
                CouponItemCard(
                    coupons = couponItem,
                    isSelected = selectedCouponItem == couponItem,
                ) {
                    selectedCouponItem = couponItem
                }
            }
        }
    }
}
