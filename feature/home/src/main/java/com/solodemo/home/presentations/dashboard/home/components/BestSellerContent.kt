package com.solodemo.home.presentations.dashboard.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.components.HorizontalGridProductShimmerLoading
import com.solodemo.home.components.HorizontalGridProductShimmerLoadingWithText
import com.solodemo.home.components.ProductsCardItems
import com.solodemo.home.presentations.products.ProductsState

@Composable
fun HomePopularContent(
    productState: ProductsState,
    errorCallback: (String) -> Unit = {},
    popularAddToCartClicked: (Cart) -> Unit,
) {
    when {
        productState.isLoading -> HorizontalGridProductShimmerLoading()
        productState.productsList != null -> {
            val popularFood = productState.productsList.map { it.foods?.first() }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.best_seller),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.best_seller_description),
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    lineHeight = 16.sp,
                )

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(850.dp)
                        .padding(top = 10.dp),
                ) {
                    items(popularFood) { popular ->
                        key(popular?.id) {
                            if (popular != null) {
                                ProductsCardItems(
                                    food = popular,
                                    insertCart = popularAddToCartClicked,
                                    showAddQuantityMinusButton = false,
                                    showRating = true,
                                )
                            }
                        }
                    }
                }
            }
        }

        productState.errorMessage != null -> {
            HorizontalGridProductShimmerLoadingWithText()
            errorCallback(productState.errorMessage)
        }
    }
}
