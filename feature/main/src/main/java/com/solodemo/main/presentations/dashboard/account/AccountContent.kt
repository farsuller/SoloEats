package com.solodemo.main.presentations.dashboard.account

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.solo.components.Constants
import com.solo.components.R
import com.solo.components.clickableWithoutRipple
import com.solo.components.getAppVersion
import com.solo.components.sendEmail
import com.solodemo.main.presentations.MainViewModel

@Composable
internal fun AccountContent(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    onPrivacyPolicyClicked: () -> Unit,
    onSupabaseLogoClicked: () -> Unit,
) {
    val context = LocalContext.current
    val accountState = viewModel.accountState
    val appVersion = getAppVersion(context)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                ) {
                    if (accountState.profile.isNotEmpty()) {
                        SubcomposeAsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = accountState.profile,
                            contentDescription = accountState.name,
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Default.PersonPin,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = accountState.name,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = accountState.email,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (accountState.isEmailVerified) {
                        Text(
                            modifier = Modifier,
                            text = "Email Verified",
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Verified")
                    } else {
                        Text(
                            modifier = Modifier,
                            text = "Email not yet verified",
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "My Account",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableWithoutRipple(
                            onClick = {
                                Toast
                                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        ),
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                            text = "Favourites",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableWithoutRipple(
                            onClick = {
                                Toast
                                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        ),
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                            text = "Saved Places",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableWithoutRipple(
                            onClick = {
                                Toast
                                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        ),
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                            text = "Coupons",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "General",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableWithoutRipple(
                            onClick = { onPrivacyPolicyClicked() },
                        ),
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                            text = "Privacy Policy",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 15.dp)
                                .clickableWithoutRipple(
                                    onClick = {
                                        sendEmail(
                                            Constants.DEVELOPER_EMAIL,
                                            Constants.EMAIL_SUBJECT,
                                            Constants.EMAIL_MESSAGE,
                                            context,
                                        )
                                    },
                                ),
                            text = "Share Feedback",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 15.dp),
                            text = "Version: $appVersion",
                            fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { onSignOutButtonClicked() },
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
                ) {
                    Text(
                        text = "Sign out",
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Powered by",
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
            )

            SubcomposeAsyncImage(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp)
                    .size(width = 140.dp, height = 40.dp)
                    .clickableWithoutRipple(
                        onClick = { onSupabaseLogoClicked() },
                    ),
                model = R.drawable.supabase_logo_light,
                contentDescription = accountState.name,
                contentScale = ContentScale.Fit,
            )
        }
    }
}
