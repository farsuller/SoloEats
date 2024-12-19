package com.solodemo.home.presentations.dashboard.account.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solo.components.R
import com.solo.components.extractNameFromEmail
import com.solodemo.home.presentations.dashboard.account.AccountState

@Composable
fun AccountHeader(accountState: AccountState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Hi, ${accountState.email?.extractNameFromEmail()}",
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}
