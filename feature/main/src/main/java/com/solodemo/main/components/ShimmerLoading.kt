package com.solodemo.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solo.components.R
import com.solo.components.component.shimmerEffect
import com.solo.components.shapes.TicketShape

@Composable
fun CouponShimmerLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        TextShimmerLoading()

        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .size(width = 150.dp, height = 20.dp)
                .shimmerEffect(),
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        ) {
            items(5) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .size(200.dp, 100.dp)
                        .clip(TicketShape())
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Composable
fun ReviewsShimmerLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        TextShimmerLoading()

        ReviewsRowCardShimmer()
    }
}

@Composable
fun ReviewsShimmerLoadingWithText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        SwipeDownContent()

        ReviewsRowCardShimmer()
    }
}

@Composable
private fun ReviewsRowCardShimmer() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        items(5) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(200.dp, 100.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .shimmerEffect(),
            )
        }
    }
}

@Composable
fun MenuCircleShimmerLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        TextShimmerLoading()
        MenuCircularRow()
    }
}

@Composable
private fun TextShimmerLoading() {
    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .size(width = 100.dp, height = 20.dp)
            .shimmerEffect(),
    )
}

@Composable
fun MenuCircleShimmerLoadingWithText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        SwipeDownContent()

        MenuCircularRow()
    }
}

@Composable
private fun MenuCircularRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        items(5) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        }
    }
}

@Composable
private fun SwipeDownContent() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = stringResource(R.string.swipe_down),
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                textAlign = TextAlign.Start,
            )

            Text(
                text = stringResource(R.string.swipe_down_description),
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                textAlign = TextAlign.Start,
            )
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
        )
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
                .size(width = 140.dp, height = 20.dp)
                .shimmerEffect(),
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(850.dp),
        ) {
            items(15) {
                ProductCardShimmer()
            }
        }
    }
}

@Composable
fun HorizontalGridProductShimmerLoadingWithText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        SwipeDownContent()

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(850.dp),
        ) {
            items(15) {
                ProductCardShimmer()
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
                .size(width = 140.dp, height = 20.dp)
                .shimmerEffect(),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .height(height = 20.dp)
                .shimmerEffect(),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .height(height = 20.dp)
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
                ProductCardShimmer()
            }
        }
    }
}

@Composable
private fun ProductCardShimmer() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(width = 200.dp, height = 280.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .shimmerEffect(),
    )
}

@Preview
@Composable
fun CouponShimmerLoadingPreview() {
    CouponShimmerLoading()
}

@Preview
@Composable
fun ReviewsShimmerLoadingPreview() {
    ReviewsShimmerLoading()
}

@Preview
@Composable
fun ReviewsShimmerLoadingWithTextPreview() {
    ReviewsShimmerLoadingWithText()
}

@Preview
@Composable
fun MenuCircleShimmerLoadingWithTextPreview() {
    MenuCircleShimmerLoadingWithText()
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
fun HorizontalGridProductShimmerLoadingWithTextPreview() {
    HorizontalGridProductShimmerLoadingWithText()
}

@Preview
@Composable
fun VerticalGridProductShimmerLoadingPreview() {
    VerticalGridProductShimmerLoading()
}
