package com.gabrielthecode.kontakt.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielthecode.kontakt.R
import com.gabrielthecode.kontakt.presentation.ui.theme.KontaktTheme
import com.gabrielthecode.kontakt.presentation.ui.theme.Typography

@Composable
fun ErrorBottomSection(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.error),
    ) {
        Row(
            modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                style = Typography.labelMedium,
                color = MaterialTheme.colorScheme.onError,
                modifier = Modifier.weight(1f),
                maxLines = 2
            )
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedButton(
                onClick = onClickRetry,
                border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant)
            ) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = Typography.labelMedium,
                    color = MaterialTheme.colorScheme.onError
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorBottomSectionPreview() {
    KontaktTheme {
        ErrorBottomSection("A lambda error message", Modifier){}
    }
}