package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.components.MainBackground
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.products.ProductsState

@Composable
internal fun HomeScreen(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit,
) {
    MainBackground()

    HomeContent(
        paddingValues = paddingValues,
        menusState = menusState,
        reviewsState = reviewsState,
        productState = productState,
        homeLazyListState = homeLazyListState,
        navigateToProductList = navigateToProductList,
        popularAddToCartClicked = popularAddToCartClicked,
    )
}
