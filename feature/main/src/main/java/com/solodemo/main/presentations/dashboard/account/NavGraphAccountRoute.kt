package com.solodemo.main.presentations.dashboard.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solo.components.Constants
import com.solo.components.routes.ScreensRoutes

fun NavGraphBuilder.accountRoute(
    paddingValues: PaddingValues,
    accountState: AccountState,
    navigateToAuth: () -> Unit,
) {
    composable(route = ScreensRoutes.Account.route) {
        val uriHandler = LocalUriHandler.current

        AccountScreen(
            accountState = accountState,
            paddingValues = paddingValues,
            onSignOutButtonClicked = {
            },
            onPrivacyPolicyClicked = {
                uriHandler.openUri(Constants.PRIVACY_POLICY_LINK)
            },
            onSupabaseLogoClicked = {
                uriHandler.openUri(Constants.SUPABASE_LINK)
            },
        )
    }
}
