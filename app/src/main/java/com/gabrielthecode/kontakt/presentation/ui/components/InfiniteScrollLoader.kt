package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteScrollLoader(modifier: Modifier) {
	CircularProgressIndicator(
		modifier = modifier
			.fillMaxWidth()
			.padding(10.dp)
			.wrapContentWidth(Alignment.CenterHorizontally)
	)
}