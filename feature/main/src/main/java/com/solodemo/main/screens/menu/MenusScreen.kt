package com.solodemo.main.screens.menu

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solodemo.main.components.HexagonBackGroundItem

import com.solodemo.supabase.model.Menu
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.solo.util.clickableWithoutRipple
import com.solodemo.main.components.HexagonImageTextItem

@Composable
fun MenusScreen(modifier: Modifier = Modifier, menus: List<Menu>) {
    val context = LocalContext.current

    val filteredMenus = menus.filter { it.isAvailable == true }
    if (filteredMenus.isNotEmpty()) {

        HexagonColumnWithOffset(offsetX = (-130).dp, offsetY = (-10).dp, menus = menus)
        HexagonColumnWithOffset(offsetX = 340.dp, offsetY = 80.dp, menus = menus)

        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy((-120).dp),
        ) {

            items(filteredMenus.size) { index ->
                val topPadding = if (index % 2 == 1) 90.dp else 0.dp
                val startOffsetX = if (index % 2 == 1) (-40).dp else 0.dp

                HexagonImageTextItem(
                    index = index,
                    menus = filteredMenus,
                    modifier = Modifier
                        .padding(top = topPadding)
                        .offset(x = startOffsetX)
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = {
                                Toast
                                    .makeText(
                                        context,
                                        "Selected ${filteredMenus[index].menuName}",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        ),
                )
            }
        }
    }
}

@Composable
fun HexagonColumnWithOffset(offsetX: Dp, offsetY: Dp, menus: List<Menu>) {
    Column(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
        repeat(menus.size) {
            HexagonBackGroundItem(modifier = Modifier.offset(x = offsetX, y = offsetY))
        }
    }
}
