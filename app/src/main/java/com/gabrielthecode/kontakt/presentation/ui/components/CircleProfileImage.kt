package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gabrielthecode.kontakt.presentation.ui.theme.KontaktTheme

@Composable
fun CircleProfileImage(imageUri: String, imageSize: Dp = 46.dp) {
    AsyncImage(
        placeholder = rememberVectorPainter(image = Icons.Outlined.AccountCircle),
        model = imageUri,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun CircleProfileImagePreview() {
    KontaktTheme {
        CircleProfileImage("https://picsum.photos/id/237/200/300")
    }
}