package com.solodemo.main.presentations.dashboard.payment.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.ui.Elevation
import com.solo.components.clickableWithoutRipple
import com.solodemo.main.model.Featured

@Composable
fun RecentTransactionCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    val scroll = rememberScrollState()
    ElevatedCard(
        modifier = modifier.padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .verticalScroll(state = scroll),
        ) {
            val popularItems = Featured.entries.toTypedArray().take(4)

            popularItems.forEachIndexed { index, recent ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .clickableWithoutRipple(
                            onClick = {
                                Toast
                                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.weight(0.5F),
                        text = recent.title,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        modifier = Modifier.weight(0.2F),
                        text = recent.price,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                    )
                }
                if (index != popularItems.size - 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                    )
                }
            }
        }
    }
}
