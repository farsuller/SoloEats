package com.solodemo.auth.presenations.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.component.CircleImageItem

@Composable
fun SignUpHeader(
    alignment: Alignment.Horizontal = Alignment.End,
    imageFile:String = Constants.StaticImages.burger,
    borderColor: Color = MaterialTheme.colorScheme.primary){

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment,

    ) {

        CircleImageItem(
            imageFile = imageFile,
            borderColor = borderColor,
            shapeSize = 270.dp,
            modifier = Modifier
        )


    }
}