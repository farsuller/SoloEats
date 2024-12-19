package com.solodemo.home.presentations.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.R
import com.solodemo.database.domain.model.Cart
import com.solodemo.home.components.MainHeaderCard
import com.solodemo.home.presentations.dashboard.home.components.HomeBannersContent
import com.solodemo.home.presentations.dashboard.home.components.HomeMenusContent
import com.solodemo.home.presentations.dashboard.home.components.HomePopularContent
import com.solodemo.home.presentations.dashboard.home.components.ReviewsContent
import com.solodemo.home.presentations.dashboard.menu.MenusState
import com.solodemo.home.presentations.products.ProductsState

@Composable
internal fun HomeContent(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit,
    errorCallback: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = homeLazyListState,
    ) {
        item {
            MainHeaderCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 160.dp),
                title = stringResource(R.string.brewing_moments),
                description = stringResource(R.string.brewing_moments_description),
                color = MaterialTheme.colorScheme.secondary,
                imagePath = Constants.StaticImages.bannerSplashCoffee,
            )

            HomeBannersContent()

            HomeMenusContent(
                menusState = menusState,
                navigateToProductList = navigateToProductList,
                errorCallback = errorCallback,
            )

            HomePopularContent(
                productState = productState,
                errorCallback = errorCallback,
                popularAddToCartClicked = popularAddToCartClicked,
            )

            ReviewsContent(
                reviewsState = reviewsState,
                errorCallback = errorCallback,
            )
        }
    }
}
