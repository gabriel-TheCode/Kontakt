package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.R
import com.gabrielthecode.kontakt.presentation.ui.theme.KontaktTheme
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun UserInfoItem(label: String, value: String, iconRes: Int) {
	Card(
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.background,
		),
		shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp)
	) {
		Column(
			modifier = Modifier.padding(12.dp)
		) {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Icon(
					painter = painterResource(id = iconRes),
					contentDescription = null,
					tint = MaterialTheme.colorScheme.tertiary,
					modifier = Modifier.size(12.dp)
				)

				Spacer(modifier = Modifier.width(4.dp))

				Text(
					text = label,
					style = Typography.labelMedium,
					color = MaterialTheme.colorScheme.tertiary,
				)
			}

			Text(
				text = value,
				style = Typography.labelLarge
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun UserInfoItemPreview() {
	KontaktTheme {
		UserInfoItem("Phone", value = "06-94-93-93-22", iconRes = R.drawable.baseline_local_phone_24)
	}
}