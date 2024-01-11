package com.solodemo.main.presentations.screens.home

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.Constants
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.state.RequestState
import com.solo.ui.Elevation

import com.solodemo.main.presentations.screens.home.components.HomeBannerCard
import com.solodemo.main.components.MainHeaderCard
import com.solodemo.main.presentations.screens.home.components.HomeMenusCard
import com.solodemo.main.presentations.screens.home.components.HomeReelsCard
import com.solodemo.main.model.Featured
import com.solodemo.main.model.HomeBanners
import com.solodemo.main.presentations.screens.home.components.HomePopularCard
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels


@Composable
internal fun HomeContent(
    paddingValues: PaddingValues,
    menus: Menus,
    reels: Reels,
    homeLazyListState: LazyListState,
    navigateToProductList: (String) -> Unit
) {

    val context = LocalContext.current

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

            // HomeReelsContent(reels, context)


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

            Spacer(modifier = Modifier.size(10.dp))

            HomeMenusContent(menus = menus, navigateToProductList = navigateToProductList)

            Spacer(modifier = Modifier.size(10.dp))

            HomePopularContent(context)

        }
    }
}

@Composable
private fun HomePopularContent(context: Context) {
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
                items(Featured.entries.toTypedArray()) { entries ->
                    HomePopularCard(
                        Modifier.padding(5.dp),
                        title = entries.title,
                        price = entries.price,
                        imagePath = entries.imagePath,
                        onAddButtonClicked = {
                            Toast.makeText(
                                context,
                                "Added ${entries.title}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
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
    when (menus) {
        is RequestState.Loading -> CircularLoadingIndicator()
        is RequestState.Success -> {
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
                Box(modifier = Modifier.fillMaxWidth()) {
                    val filteredMenus = menus.data.filter { it.isAvailable }
                    LazyRow {
                        items(filteredMenus.size) { index ->
                            HomeMenusCard(index, menus = filteredMenus, onClick = {
                                navigateToProductList(filteredMenus[index].menuName!!)
                            })
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
private fun HomeReelsContent(
    reels: Reels,
    context: Context
) {
    when (reels) {
        RequestState.Loading -> CircularLoadingIndicator()
        is RequestState.Success -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Reels",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level5),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        val filteredReels = reels.data.filter { it.isReviewed == true }
                        LazyRow {
                            items(filteredReels.size) { index ->
                                HomeReelsCard(index, reels = filteredReels, onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Selected ${filteredReels[index].name}",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
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