package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solo.components.formatToCurrency
import com.solodemo.home.presentations.dashboard.cart.CartState

@Composable
fun TotalAndPlaceOrderButtonHolder(
    placeOrderButtonClicked: () -> Unit = {},
    navigateToPlaceOrderSuccess: () -> Unit,
    cartState: CartState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(R.string.total),
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = formatToCurrency(cartState.totalPrice),
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
        }
        Button(
            onClick = {
                placeOrderButtonClicked()
                navigateToPlaceOrderSuccess()
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),

        ) {
            Text(
                text = stringResource(R.string.checkout),
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}
