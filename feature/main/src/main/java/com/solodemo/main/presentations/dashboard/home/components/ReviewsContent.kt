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
import com.solodemo.main.components.ReviewsShimmerLoading
import com.solodemo.main.components.ReviewsShimmerLoadingWithText
import com.solodemo.main.presentations.dashboard.home.ReviewsState

@Composable
fun ReviewsContent(
    reviewsState: ReviewsState,
    errorCallback: (String) -> Unit = {},
) {
    when {
        reviewsState.isLoading -> ReviewsShimmerLoading()
        reviewsState.reviewsList != null -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.reviews),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = stringResource(R.string.reviews_description),
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
                        items(reviewsState.reviewsList) { reviewsItem ->
                            key(reviewsItem.id) {
                                ReviewCards(reviewsItem)
                            }
                        }
                    }
                }
            }
        }

        reviewsState.errorMessage != null -> {
            ReviewsShimmerLoadingWithText()
            errorCallback(reviewsState.errorMessage)
        }
    }
}
