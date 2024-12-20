package com.solodemo.home.presentations.dashboard.payment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solo.components.R
import com.solo.components.theme.SoloDemoTheme
import com.solodemo.home.presentations.dashboard.payment.components.PaymentWalletCard
import com.solodemo.home.presentations.dashboard.payment.components.RecentTransactionCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PaymentScreen(
    paddingValues: PaddingValues,
) {
    val context = LocalContext.current.applicationContext

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.payment),
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = stringResource(R.string.payment_description),
                    maxLines = 2,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    lineHeight = 18.sp,
                )
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            PaymentWalletCard(
                onWalletClicked = {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                },
            )

            Spacer(modifier = Modifier.size(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.recent_transactions),
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = 18.sp,
                )

                IconButton(
                    onClick = {
                        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                    },

                ) {
                    Icon(
                        modifier = Modifier.padding(3.dp),
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            RecentTransactionCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PaymentScreenPreview() {
    SoloDemoTheme(
        darkTheme = false,
    ) {
        Surface {
            PaymentScreen(paddingValues = PaddingValues())
        }
    }
}
