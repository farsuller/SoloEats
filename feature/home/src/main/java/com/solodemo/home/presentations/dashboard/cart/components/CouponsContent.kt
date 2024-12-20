package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solo.components.R
import com.solo.components.component.DefaultErrorBox
import com.solodemo.home.components.CouponShimmerLoading
import com.solodemo.home.presentations.dashboard.cart.CartState
import com.solodemo.network.domain.model.Coupon

@Composable
fun CouponsContent(cartState: CartState) {
    var selectedCouponItem by remember { mutableStateOf<Coupon?>(null) }

    when {
        cartState.isLoading -> CouponShimmerLoading()
        cartState.couponsList.isNotEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                        text = stringResource(R.string.offers),
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )

                    Text(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                        text = stringResource(R.string.offers_description),
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    )

                    LazyRow {
                        items(cartState.couponsList) { couponItem ->
                            CouponItemCard(
                                coupon = couponItem,
                                isSelected = selectedCouponItem == couponItem,
                            ) { selectedCouponItem = couponItem }
                        }
                    }
                }
            }
        }
        cartState.errorMessage != null -> DefaultErrorBox(errorMessage = cartState.errorMessage)
    }
}
