package com.solodemo.main.screens.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skydoves.orbital.Orbital
import com.solo.util.routes.ScreensRoutes
import com.solodemo.main.components.ProductsCardItems
import com.solodemo.main.model.FoodCategory

fun NavGraphBuilder.productSelectionRoute(
    paddingValues: PaddingValues,
    foodList: List<FoodCategory>,
) {
    composable(route = ScreensRoutes.Product.route) {

        ProductListScreen(
            foodList = foodList,
            paddingValues = paddingValues
        )

    }
}

@Composable
fun ProductListScreen(
    foodList: List<FoodCategory>,
    paddingValues: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding() / 2)
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            items(foodList) { productList ->
                Orbital {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = productList.categoryName,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 28.sp,
                    )


                    Column(modifier = Modifier.padding(top = 40.dp)) {
                        productList.foods.forEach { foodItems ->
                            ProductsCardItems(foodList = foodItems)
                        }

                    }
                }
            }
        }
    }
}


