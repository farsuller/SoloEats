package com.solodemo.main.presentations.dashboard.menu

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.loading.CircularLoadingIndicator
import com.solo.components.state.RequestState
import com.solo.util.clickableWithoutRipple
import com.solodemo.main.components.MainHeaderCard
import com.solodemo.main.presentations.dashboard.menu.components.MenuHexagonItem
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.model.Menu

@Composable
fun MenuContent(
    menus: Menus,
    paddingValues: PaddingValues,
    navigateToProductList: (String) -> Unit
) {
    val density = LocalDensity.current
    var cardHeight by remember { mutableStateOf(0.dp) }
    var menuList by remember { mutableStateOf(emptyList<Menu>()) }

    MainHeaderCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 160.dp)
            .onGloballyPositioned { coordinates ->
                cardHeight = with(density) { coordinates.size.height.toDp() }
            },
        title = "Burgers & Fries Extravaganza",
        description = "Discover a world of taste with our extraordinary burgers and fries.",
        color = MaterialTheme.colorScheme.primary,
        imagePath = Constants.StaticImages.bannerBurgerFries
    )

    when (menus) {
        is RequestState.Loading -> CircularLoadingIndicator()
        is RequestState.Success -> {
            menuList = menus.data.filter { it.isAvailable }
            ShowMenuCards(
                cardHeight = cardHeight,
                filteredMenu = menuList,
                paddingValues = paddingValues,
                navigateToProductList = navigateToProductList
            )
        }

        is RequestState.Error -> {}
        else -> {}
    }
}

@Composable
fun ShowMenuCards(
    cardHeight: Dp,
    filteredMenu: List<Menu>,
    paddingValues: PaddingValues,
    navigateToProductList: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(start = 40.dp)
            .fillMaxSize()
            .padding(top = cardHeight)
            .padding(bottom = paddingValues.calculateBottomPadding()),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy((-120).dp),
    ) {

        items(filteredMenu.size) { index ->
            val topPadding = if (index % 2 == 1) 90.dp else 0.dp
            val startOffsetX = if (index % 2 == 1) (-30).dp else 0.dp

            MenuHexagonItem(
                index = index,
                menus = filteredMenu,
                borderColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = topPadding)
                    .offset(x = startOffsetX)
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            navigateToProductList(filteredMenu[index].menuName!!)
                        }
                    ),
            )
        }
    }
}