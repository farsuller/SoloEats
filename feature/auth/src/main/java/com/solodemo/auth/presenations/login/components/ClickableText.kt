package com.solodemo.auth.presenations.login.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.solo.util.clickableWithoutRipple

@Composable
fun ClickableBottomText(onClick: () -> Unit) {
    val text = buildAnnotatedString {
        append("Haven't Account? then ")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary,
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,)
        ) {
            append("Register Now")
        }
    }


    Text(
        text = text,
        modifier = Modifier
            .padding(top = 10.dp)
            .clickableWithoutRipple(
                interactionSource = MutableInteractionSource(),
                onClick = { onClick() }
            ),
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        color = MaterialTheme.colorScheme.onSurface
    )
}