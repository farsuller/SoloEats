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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.component.DefaultErrorBox
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.presentations.products.components.ProductsCardItems

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
                productsState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
                productsState.productsList != null -> {
                    val filteredProducts = remember(productsState.productsList) {
                        productsState.productsList.filter { it.name == categoryNameSelected }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = paddingValues.calculateBottomPadding()),
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,
                        ) {
                            items(filteredProducts) { categorySelected ->

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
                                            text = "${categorySelected.name}",
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
                                        text = "${categorySelected.description}",
                                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                        fontSize = 15.sp,
                                    )
                                }
                                Column {
                                    categorySelected.foods?.forEach { items ->
                                        ProductsCardItems(
                                            food = items,
                                            insertCart = addToCartItem,
                                        )
                                    }
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
