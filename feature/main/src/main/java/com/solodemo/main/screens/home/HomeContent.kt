package com.solodemo.main.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.solo.components.CircularLoadingIndicator
import com.solo.components.EmptyPage
import com.solodemo.main.components.HomeMenusCard
import com.solodemo.supabase.model.RequestState
import com.solodemo.supabase.repository.Menus


@Composable
internal fun HomeContent(
    paddingValues: PaddingValues, menus: Menus) {

    val context = LocalContext.current
    when (menus) {
        is RequestState.Loading -> {
            CircularLoadingIndicator()
        }

        is RequestState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                    val filteredMenus = menus.data.filter { it.isAvailable == true }
                    LazyRow {
                        items(filteredMenus.size) { index ->
                            HomeMenusCard(index, menus = filteredMenus, onClick = {
                                Toast
                                    .makeText(
                                        context,
                                        "Selected ${filteredMenus[index].menuName}",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            })
                        }
                    }

                }

            }
        }

        is RequestState.Error -> {

        }

        else -> {}
    }


}