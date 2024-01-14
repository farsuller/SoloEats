package com.solodemo.main.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.solodemo.main.model.MainBottomNavItem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainBottomNavBar(
    onTabSelected: (String) -> Unit,
    navController: NavHostController,
    cartCount: Int? = null
) {
    var selectedBottomNavItemIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        MainBottomNavItem.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                label = {
                    Text(
                        text = item.title,
                        color = if (index == selectedBottomNavItemIndex) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                selected = selectedBottomNavItemIndex == index,
                onClick = {
                    onTabSelected(item.title)
                    selectedBottomNavItemIndex = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                }, icon = {
                    BadgedBox(badge = {
                        if ((cartCount != null) && (cartCount != 0) && (item.title == "Cart")) {
                            Badge {
                                Text(text = "$cartCount")
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (index == selectedBottomNavItemIndex) item.selectedIcon
                            else item.unSelectedIcon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                })
        }
    }
}