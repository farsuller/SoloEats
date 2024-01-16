package com.solodemo.main.presentations.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Constants
import com.solo.components.component.shimmerEffect
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.state.RequestState
import com.solodemo.main.components.MainHeaderCard
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.model.HomeBanners
import com.solodemo.main.presentations.screens.home.components.HomeBannerCard
import com.solodemo.main.presentations.screens.home.components.HomeMenusCard
import com.solodemo.main.presentations.screens.home.components.HomePopularCard
import com.solodemo.main.presentations.screens.home.components.ReviewCards
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import com.solodemo.supabase.model.Cart
import com.solodemo.supabase.model.Menu


@Composable
internal fun HomeContent(
    paddingValues: PaddingValues,
    menus: Menus,
    reviews: Reviews,
    foodList: List<FoodCategory>,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit,
    popularAddToCartClicked: (Cart) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = homeLazyListState
    ) {

        item {
            MainHeaderCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 160.dp),
                title = "Brewing Moments\nof Joy",
                description = "Join us for a delightful experience that goes beyond the ordinary.",
                color = MaterialTheme.colorScheme.secondary,
                imagePath = Constants.StaticImages.bannerSplashCoffee
            )

            HomeBannersContent()
            Spacer(modifier = Modifier.size(10.dp))
            HomeMenusContent(menus = menus, navigateToProductList = navigateToProductList)
            Spacer(modifier = Modifier.size(10.dp))
            HomePopularContent(
                foodList = foodList,
                popularAddToCartClicked = popularAddToCartClicked
            )
            ReviewsContent(reviews = reviews)
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
    foodList: List<FoodCategory>,
    popularAddToCartClicked: (Cart) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
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
                .height(450.dp)
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                items(foodList) { category ->

                    val firstFood = category.foods.first()
                    key(category.foods.first().foodId){
                        HomePopularCard(
                            Modifier.padding(5.dp),
                            foodId = firstFood.foodId,
                            foodName = firstFood.foodName,
                            foodPrice = firstFood.price,
                            foodImage = firstFood.foodImage,
                            onAddButtonClicked = { cart: Cart ->
                                popularAddToCartClicked(cart)
                            }
                        )
                    }

                }
            }
        }

    }
}

@Composable
private fun HomeMenusContent(
    menus: Menus,
    navigateToProductList: (String) -> Unit
) {
    var menuList by remember { mutableStateOf(emptyList<Menu>()) }
    val menuLoadCount = remember { mutableIntStateOf(5) }
    when (menus) {
        is RequestState.Loading -> {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(menuLoadCount.intValue){
                    Box(
                        modifier = Modifier.padding(horizontal = 5.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .shimmerEffect()
                    )
                }
            }
        }
        is RequestState.Success -> {
            menuList = menus.data.filter { it.isAvailable }
            menuLoadCount.intValue = menus.data.size

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
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
                        .padding(top = 10.dp)
                ) {

                    LazyRow {
                        items(menuList.size) { index ->
                            key(index){
                                HomeMenusCard(index, menus = menuList, onClick = {
                                    navigateToProductList(menuList[index].menuName!!)
                                })
                            }

                        }
                    }

                }
            }
        }

        is RequestState.Error -> {}
        else -> {}
    }


}


@Composable
private fun ReviewsContent(reviews: Reviews) {
    when (reviews) {
        RequestState.Loading -> CircularLoadingIndicator()
        is RequestState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
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
                        .padding(top = 10.dp)
                ) {
                    val reviewsList = reviews.data
                    LazyRow {
                        items(reviewsList) { reviewsItem ->
                            key(reviewsItem.id) {
                                ReviewCards(reviewsItem)
                            }
                        }
                    }

                }
            }

        }

        is RequestState.Error -> {}
        else -> {}
    }
}
