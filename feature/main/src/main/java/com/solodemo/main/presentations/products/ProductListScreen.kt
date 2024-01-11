package com.solodemo.main.presentations.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodemo.main.model.FoodCategory

@Composable
fun ProductListScreen(
    foodList: List<FoodCategory>, onBackPressClicked: () -> Unit, categoryNameSelected: String
) {

    Scaffold(modifier = Modifier
        .background(MaterialTheme.colorScheme.surface)
        .statusBarsPadding()
        .navigationBarsPadding(), topBar = {}, content = { paddingValues ->
        ProductListContent(
            foodList = foodList,
            paddingValues = paddingValues,
            categoryNameSelected,
            onBackPressClicked = onBackPressClicked
        )

    })

}

