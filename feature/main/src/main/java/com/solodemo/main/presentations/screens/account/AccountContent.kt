package com.solodemo.main.presentations.screens.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
internal fun AccountContent(
    onButtonClicked: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(modifier = Modifier.size(140.dp),
            imageVector = Icons.Default.AccountCircle,
            contentScale = ContentScale.Crop,
            contentDescription = "SoloScape Logo"
        )
            Column(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {


                Column(modifier = Modifier.weight(0.2f)) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = "My Account",
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Favourites",
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Contact Number",
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Saved Address",
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column (modifier = Modifier.weight(0.3f).padding(top = 20.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = "General",
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Help Centre",
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Share Feedback",
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }


            }
            Column(
                modifier = Modifier.weight(weight = 0.1f)
                    .padding(start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { onButtonClicked() },
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary)
                ) {

                    Text(
                        text = "Sign out",
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
internal fun AccountContentPreview(){
    AccountContent(onButtonClicked = { }, paddingValues = PaddingValues())
}