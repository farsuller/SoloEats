package com.solo.solodemo.presentations.main.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainTopBar() {

    TopAppBar(

        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Home Icon",
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        title = {

        },
        actions = {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Icon",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}