package com.solodemo.main.presentations.dashboard.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    accountState: AccountState,
    onPrivacyPolicyClicked: () -> Unit,
    onSupabaseLogoClicked: () -> Unit,
) {
    AccountContent(
        paddingValues = paddingValues,
        accountState = accountState,
        onSignOutButtonClicked = onSignOutButtonClicked,
        onPrivacyPolicyClicked = onPrivacyPolicyClicked,
        onSupabaseLogoClicked = onSupabaseLogoClicked,
    )
}
