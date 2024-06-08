package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ActionButton(
	text: String,
	value: String,
	modifier: Modifier,
	icon: Int,
	onClick: (String) -> Unit
) {
	val buttonHeight = 50.dp
	val buttonWidth = 170.dp

	Row(
		modifier = modifier
			.height(buttonHeight)
			.width(buttonWidth)
			.clickable { onClick(value) },
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			painter = painterResource(id = icon),
			contentDescription = null,
			tint = Color.White
		)
		Spacer(modifier = Modifier.width(8.dp))
		Text(
			text = text,
			color = Color.White
		)
	}
}
