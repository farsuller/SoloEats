package com.solodemo.auth.presenations.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.component.HexagonImageItem

@Composable
fun LoginHeader() {
    Column(
        modifier = Modifier.offset(y = (-20).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-90).dp),
    ) {
        HexagonImageItem(
            imageFile = Constants.StaticImages.spaghetti,
            borderColor = MaterialTheme.colorScheme.primary,
            hexagonSize = 160.dp,
            modifier = Modifier.offset(x = 75.dp),
        )

        HexagonImageItem(
            imageFile = Constants.StaticImages.burger,
            borderColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.offset(x = (-65).dp),
        )

        HexagonImageItem(
            imageFile = Constants.StaticImages.pizza,
            borderColor = MaterialTheme.colorScheme.primary,
            hexagonSize = 140.dp,
            modifier = Modifier.offset(x = 70.dp),
        )
    }
}
