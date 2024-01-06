package com.solo.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HexagonColumnWithOffset(offsetX: Dp, offsetY: Dp,) {
    Column(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
        repeat(5) {
            HexagonBackGroundItem(modifier = Modifier.offset(x = offsetX, y = offsetY))
        }
    }
}