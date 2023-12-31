package com.solo.solodemo.presentations.main.screens.payment.menu

import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.solo.solodemo.components.HexagonImageTextItem
import com.solo.solodemo.model.Menus
import com.solo.solodemo.utils.clickableWithoutRipple

@Composable
fun MenuSelectionScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy((-120).dp),


        ) {

        items(Menus.entries.toTypedArray().size) { index ->
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
                            Toast.makeText(context, "Selected ${Menus.entries[index].text}", Toast.LENGTH_SHORT).show()
                        }
                    ),
            )
        }
    }
}

