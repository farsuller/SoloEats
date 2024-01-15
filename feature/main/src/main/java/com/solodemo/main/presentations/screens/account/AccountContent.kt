package com.solodemo.main.presentations.screens.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.components.Constants
import com.solo.components.state.RequestState
import com.solo.util.clickableWithoutRipple
import com.solo.util.sendEmail
import com.solodemo.supabase.domain.repository.Users


@Composable
internal fun AccountContent(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    users: Users,
    onPrivacyPolicyClicked: () -> Unit,
) {
    val context = LocalContext.current
    val userName = remember { mutableStateOf("Empty Name") }
    val userEmail = remember { mutableStateOf("Empty Email") }
    val isEmailVerified = remember { mutableStateOf(false) }
    val userProfile = remember { mutableStateOf("") }

    when (users) {
        RequestState.Loading -> {}
        is RequestState.Success -> {
            userName.value = users.data.name
            userEmail.value = users.data.email
            isEmailVerified.value = users.data.emailVerified
            userProfile.value = users.data.picture
        }

        is RequestState.Error -> {}
        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp)
                .clip(CircleShape)
        ) {
            if (userProfile.value.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userProfile.value)
                        .crossfade(true).build(),
                    contentDescription = userName.value,
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.PersonPin,
                    contentDescription = userName.value,
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = userName.value,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = userEmail.value,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            if (isEmailVerified.value) {
                Text(
                    modifier = Modifier,
                    text = "Email Verified",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Verified")
            } else {
                Text(
                    modifier = Modifier,
                    text = "Email not yet verified",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(weight = 2f)
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {


            Column(modifier = Modifier.weight(0.1f)) {
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "My Account",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Favourites",
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "General",
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.clickableWithoutRipple(
                        interactionSource = MutableInteractionSource(),
                        onClick = { onPrivacyPolicyClicked() }
                    ),
                    text = "Privacy Policy",
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickableWithoutRipple(
                            interactionSource = MutableInteractionSource(),
                            onClick = {
                                sendEmail(
                                    Constants.DEVELOPER_EMAIL,
                                    Constants.EMAIL_SUBJECT,
                                    Constants.EMAIL_MESSAGE,
                                    context
                                )
                            }
                        ),
                    text = "Share Feedback",
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }

        }
        Column(
            modifier = Modifier
                .weight(weight = 0.2f)
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                onClick = { onSignOutButtonClicked() },
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
            ) {

                Text(
                    text = "Sign out",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}