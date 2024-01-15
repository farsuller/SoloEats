package com.solodemo.main.presentations.screens.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.main.presentations.MainViewModel
import com.solodemo.supabase.domain.repository.Users

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    onPrivacyPolicyClicked: () -> Unit,
) {
    AccountContent(
        paddingValues = paddingValues,
        onSignOutButtonClicked = onSignOutButtonClicked,
        viewModel = viewModel,
        onPrivacyPolicyClicked = onPrivacyPolicyClicked,
    )

}
