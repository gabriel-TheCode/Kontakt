package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gabrielthecode.kontakt.R
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun ErrorPage(
	title: String,
	message: String,
	lottieRes: Int,
	modifier: Modifier = Modifier,
	onClickRetry: () -> Unit
) {
	Column(
		modifier = modifier.padding(10.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))

		LottieAnimation(
			composition = composition,
			modifier = Modifier
				.height(180.dp)
				.fillMaxWidth()
		)

		Text(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			text = title,
			style = Typography.titleMedium,
			color = MaterialTheme.colorScheme.error,
			maxLines = 2
		)

		Spacer(modifier = Modifier.height(8.dp))

		Text(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			text = message,
			style = Typography.labelLarge,
			color = MaterialTheme.colorScheme.secondary
		)

		Spacer(modifier = Modifier.height(24.dp))

		OutlinedButton(
			onClick = onClickRetry, border = BorderStroke(
				width = 1.dp,
				color = MaterialTheme.colorScheme.secondary
			)
		) {
			Text(
				text = stringResource(id = R.string.retry),
				style = Typography.labelLarge,
				color = MaterialTheme.colorScheme.secondary
			)
		}
	}
}