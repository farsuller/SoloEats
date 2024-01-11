package com.solodemo.main.presentations.products.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.solo.ui.Elevation
import com.solo.util.clickableWithoutRipple
import com.solodemo.supabase.model.Review


@Composable
fun ReviewCard(index : Int,
               reviews: List<Review>,
               onClick :()-> Unit) {


}

@Preview(showBackground = true)
@Composable
internal fun ReviewCardPreview(){
    ReviewCard(index = 1, reviews = arrayListOf(), onClick = {})
}