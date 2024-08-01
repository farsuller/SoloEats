package com.solodemo.main.presentations.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.orbital.Orbital
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.main.presentations.products.components.ProductsCardItems

@Composable
fun ProductListContent(
    foodList: List<FoodCategory>,
    paddingValues: PaddingValues,
    categoryNameSelected: String,
    onBackPressClicked: () -> Unit,
    mainViewModel: MainViewModel,
    onSuccess: () -> Unit,
    onError: (String) -> Unit,
) {
    val filteredProducts = remember(foodList) {
        foodList.filter { it.categoryName == categoryNameSelected }
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
            items(filteredProducts) { productList ->

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
                            text = productList.categoryName,
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
                        text = productList.categoryDescription,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = 15.sp,
                    )
                }

                Orbital(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                    Column {
                        productList.foods.forEach { foodItems ->
                            ProductsCardItems(
                                foodList = foodItems,
                                mainViewModel = mainViewModel,
                                onSuccess = onSuccess,
                                onError = onError,
                            )
                        }
                    }
                }
            }
        }
    }
}
