package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun ActionButton(
	text: String,
	value: String,
	modifier: Modifier,
	iconRes: Int,
	onClick: (String) -> Unit
) {
	val buttonHeight = 48.dp
	val buttonWidth = 146.dp

	Row(
		modifier = modifier
			.height(buttonHeight)
			.width(buttonWidth)
			.clickable { onClick(value) },
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			modifier = Modifier.size(18.dp),
			painter = painterResource(id = iconRes),
			contentDescription = null,
			tint = Color.White
		)
		Spacer(modifier = Modifier.width(8.dp))
		Text(
			style = Typography.headlineSmall,
			text = text,
			color = Color.White
		)
	}
}
