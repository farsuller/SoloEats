package com.solodemo.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AddToCartButton(
    addToCartClicked: () -> Unit,
) {
    Button(
        onClick = addToCartClicked,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
    ) {
        Text(
            text = "Add to Cart",
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = MaterialTheme.colorScheme.surface,
        )
    }
}
