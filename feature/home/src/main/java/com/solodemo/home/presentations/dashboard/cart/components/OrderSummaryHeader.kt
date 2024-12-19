package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solo.components.clickableWithoutRipple
import kotlinx.coroutines.launch

@Composable
fun OrderSummaryHeader() {
    val tooltipState = rememberTooltipState()

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier,
            text = "Order Summary",
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )

        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip {
                    Text("Swipe to Delete")
                }
            },
            state = tooltipState,
        ) {
            Icon(
                modifier = Modifier.clickableWithoutRipple(
                    onClick = { scope.launch { tooltipState.show() } },
                ),
                imageVector = Icons.Filled.Info,
                contentDescription = null,
            )
        }
    }
}
