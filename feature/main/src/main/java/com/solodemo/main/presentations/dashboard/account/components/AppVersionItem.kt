package com.solodemo.main.presentations.dashboard.account.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppVersionItem(appVersion: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 15.dp),
                text = "Version: $appVersion",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
