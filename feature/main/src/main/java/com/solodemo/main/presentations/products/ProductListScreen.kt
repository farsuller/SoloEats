package com.solodemo.main.presentations.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.component.DefaultErrorBox
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.components.ProductsCardItems
import com.solodemo.main.components.VerticalGridProductShimmerLoading

@Composable
fun ProductListScreen(
    productsState: ProductsState,
    onBackPressClicked: () -> Unit,
    categoryNameSelected: String,
    addToCartItem: (Cart) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {},
        content = { paddingValues ->

            when {
                productsState.isLoading -> VerticalGridProductShimmerLoading()

                productsState.productsList != null -> {
                    val filteredProducts = productsState.productsList.filter { it.name == categoryNameSelected }
                    val categoryName = filteredProducts.firstOrNull()?.name
                    val categoryDescription = filteredProducts.firstOrNull()?.description
                    val foodList = filteredProducts.flatMap { it.foods ?: emptyList() }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding()),
                    ) {
                        Column {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 15.dp, bottom = 15.dp, start = 15.dp),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = "$categoryName",
                                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                        fontSize = 28.sp,
                                    )

                                    IconButton(
                                        modifier = Modifier.padding(end = 10.dp),
                                        onClick = { onBackPressClicked() },
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = null,
                                            tint = Color.Black,
                                        )
                                    }
                                }

                                Text(
                                    modifier = Modifier,
                                    text = "$categoryDescription",
                                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                    fontSize = 15.sp,
                                )
                            }

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.padding(top = 15.dp),
                            ) {
                                items(foodList) { food ->
                                    ProductsCardItems(
                                        food = food,
                                        insertCart = addToCartItem,
                                        showAddQuantityMinusButton = true,
                                        showRating = false,
                                    )
                                }
                            }
                        }
                    }
                }
                productsState.errorMessage != null -> DefaultErrorBox(errorMessage = productsState.errorMessage)
            }
        },
    )
}
