package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(
	modifier: Modifier = Modifier,
	title: String,
	canNavigateBack: Boolean,
	navigateUp: () -> Unit = {},
	actions: @Composable () -> Unit = {}
) {
	if (canNavigateBack) {
		TopAppBar(
			title = {
				Text(text = title, color = MaterialTheme.colorScheme.primary)
			},
			actions = { actions() },
			navigationIcon = {
				IconButton(onClick = navigateUp) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack,
						contentDescription = null,
						tint = MaterialTheme.colorScheme.primary
					)
				}
			},
			modifier = modifier
		)
	} else {
		TopAppBar(
			title = {
				Text(text = title)
			},
			actions = { actions() },
			modifier = modifier
		)
	}
}