package com.solodemo.auth.presenations.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.component.CircleImageItem
import com.solo.components.component.HexagonImageItem

@Composable
fun SignUpHeader(){

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,

    ) {

        CircleImageItem(
            imageFile = Constants.LoginImages.burger,
            borderColor = MaterialTheme.colorScheme.primary,
            shapeSize = 270.dp,
            modifier = Modifier
        )


    }
}