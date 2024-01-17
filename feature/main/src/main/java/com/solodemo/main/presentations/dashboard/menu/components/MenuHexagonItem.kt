package com.solodemo.main.presentations.dashboard.menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.solo.components.shapes.HexagonShape
import com.solo.components.shapes.drawCustomHexagonPath
import com.solodemo.supabase.model.Menu

@Composable
fun MenuHexagonItem(
    index: Int,
    modifier: Modifier = Modifier,
    menus: List<Menu>,
    borderColor: Color
) {
    Box(modifier = modifier
        .size(200.dp)
        .rotate(30f)
        .drawWithContent {
            drawContent()
            drawPath(
                path = drawCustomHexagonPath(size),
                color = borderColor,
                style = Stroke(
                    width = 7.dp.toPx(),
                    pathEffect = PathEffect.cornerPathEffect(40f)
                )
            )
        }
        .wrapContentSize()
    ) {



        Box(modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                shape = HexagonShape()
                clip = true
            }
        ) {

            AsyncImage(
                modifier = Modifier
                    .rotate(-30f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(menus[index].menuImage)
                    .crossfade(true).build(),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop
            )

            TextItem(text = menus[index].menuName)
        }

    }
}

@Composable
fun TextItem(text: String? = null) {

    if (text != null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)) ){
            Text(modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 50.dp)
                .rotate(-30F),
                text = text,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
            )
        }

    }

}