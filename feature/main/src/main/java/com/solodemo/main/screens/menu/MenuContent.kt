package com.solodemo.main.screens.menu

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.solodemo.supabase.model.Menu
import androidx.compose.ui.platform.LocalContext
import com.solo.util.clickableWithoutRipple
import com.solodemo.main.components.MenuHexagonItem

@Composable
fun MenuContent(filteredMenus: List<Menu>, paddingValues: PaddingValues){
    val context = LocalContext.current

    if (filteredMenus.isNotEmpty()) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 40.dp)
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .padding(bottom = paddingValues.calculateBottomPadding()),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy((-120).dp),
        ) {

            items(filteredMenus.size) { index ->
                val topPadding = if (index % 2 == 1) 90.dp else 0.dp
                val startOffsetX = if (index % 2 == 1) (-30).dp else 0.dp

                MenuHexagonItem(
                    index = index,
                    menus = filteredMenus,
                    borderColor = MaterialTheme.colorScheme.primary,
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