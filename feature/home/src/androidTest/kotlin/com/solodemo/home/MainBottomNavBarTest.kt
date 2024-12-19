package com.solodemo.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.solodemo.home.components.MainBottomNavBar
import com.solodemo.home.model.MainBottomNavItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainBottomNavBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bottomNavBar_displaysItems_andHandlesBadge() {
        val cartCount = 5
        lateinit var navController: NavHostController

        composeTestRule.setContent {
            navController = rememberNavController()
            MainBottomNavBar(
                onTabSelected = { },
                navController = navController,
                cartCount = cartCount,
            )
        }

        MainBottomNavItem.entries.forEach { item ->
            composeTestRule.onNodeWithText(item.title).assertExists()
        }

        composeTestRule.onNodeWithText(cartCount.toString()).assertExists()
    }

    @Test
    fun bottomNavBar_navigatesOnTabClick() {
        lateinit var navController: NavHostController

        composeTestRule.setContent {
            navController = rememberNavController()
            MainBottomNavBar(
                onTabSelected = { },
                navController = navController,
                cartCount = null,
            )
        }

        MainBottomNavItem.entries.forEach { item ->
            composeTestRule.onNodeWithText(item.title).performClick()
            composeTestRule.waitForIdle()
            assert(navController.currentBackStackEntry?.destination?.route == item.route)
        }
    }
}
