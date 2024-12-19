package com.solodemo.home.presentations.dashboard.account.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountItemSubHeader(@StringRes text: Int) {
    Text(
        modifier = Modifier.padding(bottom = 5.dp),
        text = stringResource(text),
        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        color = MaterialTheme.colorScheme.onSurface,
    )
}
