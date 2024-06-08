package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun HeaderSection(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            title,
            color = Color.Black,
            style = Typography.titleLarge,
            modifier = Modifier.padding(start = 8.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            description,
            color = Color.Gray,
            style = Typography.titleSmall,
            modifier = Modifier.padding(start = 8.dp),
        )
    }

}