package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun UserContactItem(
	contact: UserContactUIModel,
	modifier: Modifier,
	onContactClick: (UserContactUIModel) -> Unit
) {
	Column {
		Row(
			modifier = modifier
				.fillMaxWidth()
				.clickable { onContactClick(contact) }
				.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			CircleProfileImage(
				imageUri = contact.normalPicture,
			)
			Spacer(modifier = Modifier.width(16.dp))
			Column {
				Text(
					text = "${contact.firstname} ${contact.lastname}",
					style = Typography.labelLarge
				)
				Text(text = contact.phone, style = Typography.labelMedium)
			}
		}
	}
}