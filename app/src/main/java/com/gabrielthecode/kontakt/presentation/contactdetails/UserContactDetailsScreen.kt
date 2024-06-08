package com.gabrielthecode.kontakt.presentation.contactdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.R
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsState.InitialState
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsState.LoadedState
import com.gabrielthecode.kontakt.presentation.ui.components.ActionButton
import com.gabrielthecode.kontakt.presentation.ui.components.CircleProfileImage
import com.gabrielthecode.kontakt.presentation.ui.components.NavTopBar
import com.gabrielthecode.kontakt.presentation.ui.theme.BlueBackground
import com.gabrielthecode.kontakt.presentation.ui.theme.BlueText
import com.gabrielthecode.kontakt.presentation.ui.theme.KontaktTheme
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
internal fun UserContactDetailsScreen(
	state: UserContactDetailsState,
	onAppBarIconClick: () -> Unit,
	onCallClick: (String) -> Unit,
	onSmsClick: (String) -> Unit
) {
	when (state) {
		is LoadedState -> UserContactDetailsScreenLoadedState(
			state = state,
			onAppBarIconClick = onAppBarIconClick,
			onCallClick = onCallClick,
			onSmsClick = onSmsClick
		)
		InitialState -> Unit
	}
}

@Composable
private fun UserContactDetailsScreenLoadedState(
	state: LoadedState,
	onAppBarIconClick: () -> Unit,
	onCallClick: (String) -> Unit,
	onSmsClick: (String) -> Unit
) {
	//var isFavorite by remember { mutableStateOf(state.uiModel.favorite) }
	KontaktTheme {
		Scaffold(
			topBar = {
				NavTopBar(
					title = stringResource(id = R.string.app_name),
					canNavigateBack = true,
					navigateUp = { onAppBarIconClick() }
				)
			}
		) {
			Surface(
				modifier = Modifier
					.fillMaxSize()
					.padding(it),
				color = BlueBackground
			) {
				Column(
					Modifier
						.padding(8.dp)
						.verticalScroll(rememberScrollState()),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					state.uiModel.apply {
						CircleProfileImage(
							imageUri = largePicture,
							imageSize = 132.dp,
						)

						Spacer(modifier = Modifier.height(16.dp))

						Text(
							text = "$firstname $lastname",
							style = Typography.titleLarge,
							color = BlueText,
							modifier = Modifier.align(Alignment.CenterHorizontally)
						)

						Text(
							text = email,
							style = Typography.labelLarge,
							color = Color.Gray,
							modifier = Modifier.align(Alignment.CenterHorizontally)
						)

						Spacer(modifier = Modifier.height(16.dp))

						Row(
							modifier = Modifier
								.fillMaxWidth(),
							horizontalArrangement = Arrangement.Center
						) {
							ActionButton(
								text = "Message",
								modifier =
								Modifier.background(
									shape = RoundedCornerShape(
										topStartPercent = 20,
										bottomStartPercent = 20
									),
									color = BlueText
								),
								icon = R.drawable.baseline_message_24,
								value = phone,
							) {
								onSmsClick(phone)
							}

							Spacer(modifier = Modifier.width(4.dp))

							ActionButton(
								text = "Call",
								modifier =
								Modifier.background(
									shape = RoundedCornerShape(
										topEndPercent = 20,
										bottomEndPercent = 20
									),
									color = BlueText
								),
								icon = R.drawable.baseline_call_24,
								value = phone,
							) {
								onCallClick(phone)
							}
						}

						Spacer(modifier = Modifier.height(24.dp))
						UserInfoItem(label = "Phone", value = phone)
						UserInfoItem(label = "Gender", value = gender)
						UserInfoItem(label = "Birth date", value = birthdate)
						UserInfoItem(
							label = "Address",
							value = "$street, $city, ${this.state}, $country"
						)
						UserInfoItem(label = "Registration date", value = registered)
					}
				}
			}
		}
	}
}

@Composable
fun ActionButtons(
	onCallClick: (String) -> Unit,
	onMessageClick: (String) -> Unit,
	phone: String
) {
}

@Composable
fun UserInfoItem(label: String, value: String) {
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
			Text(
				text = label,
				style = Typography.headlineSmall,
				color = Color.Gray
			)
			Text(
				text = value,
				style = Typography.labelLarge,
				color = BlueText
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
	val user = UserContactUIModel(
		uuid = "1234",
		firstname = "John",
		lastname = "Doe",
		birthdate = "2020-01-01",
		email = "john.doe@example.com",
		smallPicture = "",
		normalPicture = "",
		largePicture = "https://randomuser.me/api/portraits/men/1.jpg",
		phone = "123-456-7890",
		gender = "male",
		street = "123 Main St",
		city = "Sample City",
		state = "Sample State",
		country = "Sample Country",
		registered = "2020-01-01",
		favorite = true
	)
	KontaktTheme {
		UserContactDetailsScreenLoadedState(LoadedState(user), {}, {}) {}
	}
}

@Composable
@Preview
fun CardSample() {
	Card(Modifier.size(width = 180.dp, height = 100.dp)) {
		Box(Modifier.fillMaxSize()) {
			Text("Card content", Modifier.align(Alignment.Center))
		}
	}
}