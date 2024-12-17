package com.solodemo.main.presentations.dashboard.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Constants
import com.solo.components.component.DefaultErrorBox
import com.solo.components.component.shimmerEffect
import com.solo.components.loading.CircularLoadingIndicator
import com.solodemo.database.domain.model.Cart
import com.solodemo.main.components.MainHeaderCard
import com.solodemo.main.model.HomeBanners
import com.solodemo.main.presentations.dashboard.home.components.HomeBannerCard
import com.solodemo.main.presentations.dashboard.home.components.HomeMenusCard
import com.solodemo.main.presentations.dashboard.home.components.HomePopularCard
import com.solodemo.main.presentations.dashboard.home.components.ReviewCards
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.products.ProductsState

@Composable
internal fun HomeContent(
    paddingValues: PaddingValues,
    menusState: MenusState,
    reviewsState: ReviewsState,
    productState: ProductsState,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = homeLazyListState,
    ) {
        item {
            MainHeaderCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 160.dp),
                title = "Brewing Moments\nof Joy",
                description = "Join us for a delightful experience that goes beyond the ordinary.",
                color = MaterialTheme.colorScheme.secondary,
                imagePath = Constants.StaticImages.bannerSplashCoffee,
            )

            HomeBannersContent()
            Spacer(modifier = Modifier.size(10.dp))
            HomeMenusContent(menusState = menusState, navigateToProductList = navigateToProductList)
            Spacer(modifier = Modifier.size(10.dp))
            HomePopularContent(
                productState = productState,
                popularAddToCartClicked = popularAddToCartClicked,
            )
            ReviewsContent(reviewsState = reviewsState)
        }
    }
}

@Composable
private fun HomeBannersContent() {
    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        this.items(HomeBanners.entries.toTypedArray()) { entries ->
            HomeBannerCard(
                Modifier.padding(horizontal = 5.dp),
                title = entries.title,
                color = entries.color,
                imagePath = entries.imagePath,
            )
        }
    }
}

@Composable
private fun HomePopularContent(
    productState: ProductsState,
    popularAddToCartClicked: (Cart) -> Unit,
) {
    when {
        productState.isLoading -> {}
        productState.productsList != null -> {
            val popularFood = productState.productsList.map { it.foods?.first() }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Popular",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                ) {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(3),
                        modifier = Modifier.padding(top = 10.dp),
                    ) {
                        items(popularFood) { popular ->

                            key(popular?.id) {
                                if (popular != null) {
                                    HomePopularCard(
                                        Modifier.padding(5.dp),
                                        food = popular,
                                        onAddButtonClicked = { cart: Cart ->
                                            popularAddToCartClicked(cart)
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        productState.errorMessage != null -> DefaultErrorBox(errorMessage = productState.errorMessage)
    }
}

@Composable
private fun HomeMenusContent(
    menusState: MenusState,
    navigateToProductList: (String) -> Unit,
) {
    when {
        menusState.isLoading -> {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(5) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .shimmerEffect(),
                    )
                }
            }
        }

        menusState.menusList != null -> {
            val menuList = menusState.menusList.filter { it.isAvailable }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Menu",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    LazyRow {
                        items(menuList) { menu ->
                            key(menu.id) {
                                HomeMenusCard(menus = menu, onClick = navigateToProductList)
                            }
                        }
                    }
                }
            }
        }

        menusState.errorMessage != null -> DefaultErrorBox(errorMessage = menusState.errorMessage)
    }
}

@Composable
private fun ReviewsContent(reviewsState: ReviewsState) {
    when {
        reviewsState.isLoading -> CircularLoadingIndicator()
        reviewsState.reviewsList != null -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Reviews",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    LazyRow {
                        items(reviewsState.reviewsList) { reviewsItem ->
                            key(reviewsItem.id) {
                                ReviewCards(reviewsItem)
                            }
                        }
                    }
                }
            }
        }

        reviewsState.errorMessage != null -> DefaultErrorBox(errorMessage = reviewsState.errorMessage)
    }
}
