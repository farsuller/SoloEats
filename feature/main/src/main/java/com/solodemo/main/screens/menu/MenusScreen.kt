package com.solodemo.main.screens.menu

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.solodemo.main.components.HexagonImageTextItem
import com.solodemo.main.components.HexagonBackGroundItem
import com.solo.util.clickableWithoutRipple
import com.solodemo.main.model.Menus

@Composable
fun MenusScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val menuList = Menus.entries.toTypedArray()


    Column(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
        repeat(menuList.size){
            HexagonBackGroundItem(modifier = Modifier.offset(x = (-130).dp, y = (-10).dp))
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
        repeat(menuList.size){
            HexagonBackGroundItem(modifier = Modifier.offset(x = 340.dp, y = 80.dp))
        }
    }
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy((-120).dp),


        ) {

        items(menuList.size) { index ->
            val topPadding = if (index % 2 == 1) 90.dp else 0.dp
            val startOffsetX = if (index % 2 == 1) (-40).dp else 0.dp


            HexagonImageTextItem(
                index = index,
                modifier = Modifier
                    .padding(top = topPadding)
                    .offset(x = startOffsetX)
                    .clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = {
                            Toast
                                .makeText(
                                    context,
                                    "Selected ${Menus.entries[index].text}",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    ),
            )
        }
    }


}

