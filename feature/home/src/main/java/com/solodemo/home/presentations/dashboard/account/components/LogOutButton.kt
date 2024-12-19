package com.solodemo.home.presentations.dashboard.account.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.solo.components.R

@Composable
fun LogOutButton(onSignOutButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier.padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Bottom,
    ) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            onClick = { onSignOutButtonClicked() },
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text(
                text = stringResource(R.string.log_out),
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
