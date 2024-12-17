package com.solodemo.auth.presenations.login.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solo.components.component.ClickableBottomText

@Composable
fun LoginBottomItems(
    onSignUpButtonClicked: () -> Unit,
) {
    Text(
        text = "OR",
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        color = MaterialTheme.colorScheme.onSurface,
    )

//    GoogleButton(
//        modifier = Modifier.padding(top = 10.dp),
//        loadingState = authViewModel.composeAuthState.value,
//        onClick = {
//            authViewModel.setGoogleClicked(true)
//            authViewModel.setComposeAuthLoading(true)
//            googleSignIn.startFlow()
//        },
//    )

    ClickableBottomText(
        onClick = { onSignUpButtonClicked() },
        appendText = "Haven't Account? then ",
        appendHighlightText = "Register Now",
    )
}
