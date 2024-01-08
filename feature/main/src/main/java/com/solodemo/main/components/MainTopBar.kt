package com.solodemo.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainTopBar(selectedTab: String) {

    TopAppBar(
        navigationIcon = {
//            Icon(
//                imageVector = Icons.Default.Menu,
//                contentDescription = "Home Icon",
//                tint = if(selectedTab == "Home")MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
//            )
        },
        title = {

        },
        actions = {
//            Icon(
//                imageVector = Icons.Default.MoreVert,
//                contentDescription = "More Icon",
//                tint = if(selectedTab == "Home")MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
//            )
        },
        colors = topAppBarColors(containerColor = if(selectedTab == "Account") MaterialTheme.colorScheme.secondary else Color.Transparent)
    )
}