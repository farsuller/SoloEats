package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.solodemo.main.components.MainBackground
import com.solodemo.main.model.FoodCategory
import com.solodemo.supabase.domain.model.Cart
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews

@Composable
internal fun HomeScreen(
    paddingValues: PaddingValues,
    menus: Menus,
    reviews: Reviews,
    foodList: List<FoodCategory>,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit,
) {
    MainBackground()

    HomeContent(
        paddingValues = paddingValues,
        menus = menus,
        foodList = foodList,
        homeLazyListState = homeLazyListState,
        navigateToProductList = navigateToProductList,
        reviews = reviews,
        popularAddToCartClicked = popularAddToCartClicked,
    )
}
