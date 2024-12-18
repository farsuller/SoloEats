package com.solodemo.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solo.components.component.shimmerEffect

@Composable
fun MenuCircleShimmerLoading() {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(5) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        }
    }
}

@Composable
fun HorizontalGridProductShimmerLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(width = 140.dp, height = 30.dp)
                .shimmerEffect(),
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(850.dp),
        ) {
            items(15) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(width = 200.dp, height = 280.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Composable
fun VerticalGridProductShimmerLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(width = 140.dp, height = 30.dp)
                .shimmerEffect(),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .height(height = 30.dp)
                .shimmerEffect(),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                .height(height = 30.dp)
                .shimmerEffect(),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(850.dp),
        ) {
            items(15) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(width = 200.dp, height = 280.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuCircleShimmerLoadingPreview() {
    MenuCircleShimmerLoading()
}

@Preview
@Composable
fun HorizontalGridProductShimmerLoadingPreview() {
    HorizontalGridProductShimmerLoading()
}

@Preview
@Composable
fun VerticalGridProductShimmerLoadingPreview() {
    VerticalGridProductShimmerLoading()
}
