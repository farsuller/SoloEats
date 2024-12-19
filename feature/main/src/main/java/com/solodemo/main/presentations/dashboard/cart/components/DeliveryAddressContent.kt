package com.solodemo.main.presentations.dashboard.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solo.components.extractNameFromEmail
import com.solodemo.main.presentations.dashboard.account.AccountState

@Composable
fun DeliveryAddressContent(accountState: AccountState) {
    val focusManager = LocalFocusManager.current
    var addressState by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.delivery_address),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
                text = "Hi, ${accountState.email.extractNameFromEmail()}",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = addressState,
                onValueChange = { addressState = it },
                label = {
                    Text(
                        text = stringResource(R.string.address),
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
        }
    }
}
