package com.solodemo.main.presentations.dashboard.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.main.presentations.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    onPrivacyPolicyClicked: () -> Unit,
    onSupabaseLogoClicked: () -> Unit
) {
    AccountContent(
        paddingValues = paddingValues,
        onSignOutButtonClicked = onSignOutButtonClicked,
        viewModel = viewModel,
        onPrivacyPolicyClicked = onPrivacyPolicyClicked,
        onSupabaseLogoClicked = onSupabaseLogoClicked
    )

}
