package com.solodemo.main.screens.home

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.state.RequestState
import com.solo.ui.Elevation
import com.solodemo.main.components.HomeBannerCard
import com.solodemo.main.components.HomeHeaderCard
import com.solodemo.main.components.HomeMenusCard
import com.solodemo.main.components.HomeReelsCard
import com.solodemo.main.components.MainBackground
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels


@Composable
internal fun HomeContent(
    paddingValues: PaddingValues,
    menus: Menus,
    reels: Reels,
    homeLazyListState: LazyListState,
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
          //  .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = homeLazyListState
    ) {

        item {
            HomeHeaderCard(
                modifier = Modifier.fillMaxWidth().height(height = 160.dp),
                title ="Brewing Moments\nof Joy",
                description = "Join us for a delightful experience that goes beyond the ordinary.",
                color = MaterialTheme.colorScheme.secondary,
                imagePath=Constants.StaticImages.bannerSplashCoffee)

            HomeReelsContent(reels, context)

            HomeMenusContent(menus, context)


            LazyRow(modifier = Modifier.padding(top = 10.dp)){
                item {
                    HomeBannerCard(
                        Modifier.padding(horizontal = 5.dp),
                        title ="Bite into Boldness",
                        color = MaterialTheme.colorScheme.primary,
                        imagePath=Constants.StaticImages.bannerBurger)
                    HomeBannerCard(
                        Modifier.padding(horizontal = 5.dp),
                        title ="Slice into Perfection",
                        color = MaterialTheme.colorScheme.onTertiary,
                        imagePath=Constants.StaticImages.bannerPizza)

                    HomeBannerCard(
                        Modifier.padding(horizontal = 5.dp),
                        title ="Awaken Your Senses",
                        color = MaterialTheme.colorScheme.secondary,
                        imagePath=Constants.StaticImages.bannerCoffee)

                }

            }
        }

    }

}

@Composable
private fun HomeMenusContent(
    menus: Menus,
    context: Context
) {
    when (menus) {
        is RequestState.Loading -> CircularLoadingIndicator()
        is RequestState.Success -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Menu",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    val filteredMenus = menus.data.filter { it.isAvailable == true }
                    LazyRow {
                        items(filteredMenus.size) { index ->
                            HomeMenusCard(index, menus = filteredMenus, onClick = {
                                Toast
                                    .makeText(
                                        context,
                                        "Selected ${filteredMenus[index].menuName}",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
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
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)) {
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