package com.solodemo.main.presentations.screens.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.solodemo.supabase.domain.repository.Users

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    users: Users
) {
    AccountContent(
        paddingValues = paddingValues,
        onButtonClicked = onButtonClicked,
        users = users
    )

}
