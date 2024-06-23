package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.ui.theme.BlueGray100
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun UserContactItem(
	contact: UserContactUIModel,
	modifier: Modifier,
	onContactClick: (UserContactUIModel) -> Unit
) {
	Column(Modifier.padding(horizontal = 10.dp)) {
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

		HorizontalDivider(
			color = BlueGray100,
			thickness = 1.dp,
			modifier = Modifier.padding(start = 78.dp)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun UserContactItemPreview() {
	UserContactItem(
		contact = UserContactUIModel(
			uuid = "1",
			firstname = "John",
			lastname = "Doe",
			birthdate = "1990-01-01",
			email = "john.doe@example.com",
			smallPicture = "http://example.com/small.jpg",
			normalPicture = "http://example.com/normal.jpg",
			largePicture = "http://example.com/large.jpg",
			phone = "123-456-7890",
			gender = "male",
			street = "123 Main St",
			city = "Hometown",
			state = "CA",
			country = "USA",
			registered = "2020-01-01",
			favorite = false
		),
		modifier = Modifier,
		onContactClick = {}
	)
}