package com.solodemo.auth.presenations.forgot.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.component.HexagonImageItem

@Composable
fun BottomHeader(
    alignment: Alignment.Horizontal = Alignment.End,
    imageFile: String = Constants.StaticImages.burger,
    borderColor: Color = MaterialTheme.colorScheme.primary,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment,

    ) {
        HexagonImageItem(
            imageFile = imageFile,
            borderColor = borderColor,
            hexagonSize = 360.dp,
            modifier = Modifier.offset(x = 75.dp),
        )
    }
}
