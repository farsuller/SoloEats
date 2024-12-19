package com.solodemo.home.presentations.dashboard.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R

@Composable
fun PaymentMethodContent() {
    var selectedOption by remember { mutableStateOf("Cash") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.payment_details),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )

            Text(
                text = stringResource(R.string.payment_details_description),
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                lineHeight = 16.sp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp, 20.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = R.drawable.ic_peso_currency),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f),
                    text = stringResource(R.string.cash),
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )

                RadioButton(
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.secondary),
                    selected = selectedOption == stringResource(R.string.cash),
                    onClick = { selectedOption = "Cash" },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_card_payment),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .weight(1f),
                    text = stringResource(R.string.card),
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )

                RadioButton(
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.secondary),
                    selected = selectedOption == stringResource(R.string.card),
                    onClick = { selectedOption = "Card" },
                )
            }
        }
    }
}
