package com.solodemo.home.presentations.dashboard.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.components.MainBackground
import com.solodemo.home.presentations.dashboard.menu.MenusState
import com.solodemo.home.presentations.products.ProductsState

@Composable
internal fun HomeScreen(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit,
    errorCallback: (String) -> Unit = {},
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
        errorCallback = errorCallback,
    )
}
