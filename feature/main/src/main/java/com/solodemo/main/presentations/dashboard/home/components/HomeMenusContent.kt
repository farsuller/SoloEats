package com.solodemo.main.presentations.dashboard.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solodemo.main.components.MenuCircleShimmerLoading
import com.solodemo.main.components.MenuCircleShimmerLoadingWithText
import com.solodemo.main.presentations.dashboard.menu.MenusState

@Composable
fun HomeMenusContent(
    menusState: MenusState,
    navigateToProductList: (String) -> Unit,
    errorCallback: (String) -> Unit = {},
) {
    when {
        menusState.isLoading -> MenuCircleShimmerLoading()
        menusState.menusList != null -> {
            val menuList = menusState.menusList.filter { it.isAvailable }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.menu),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.menu_description),
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    lineHeight = 16.sp,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    LazyRow {
                        items(menuList) { menu ->
                            key(menu.id) {
                                HomeMenusCard(menus = menu, onClick = navigateToProductList)
                            }
                        }
                    }
                }
            }
        }

        menusState.errorMessage != null -> {
            MenuCircleShimmerLoadingWithText()
            errorCallback(menusState.errorMessage)
        }
    }
}
