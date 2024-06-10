package com.gabrielthecode.kontakt.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gabrielthecode.kontakt.R

val MontserratFamily = FontFamily(
	Font(R.font.montserrat_light, FontWeight.Light),
	Font(R.font.montserrat_regular, FontWeight.Normal),
	Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
	Font(R.font.montserrat_medium, FontWeight.Medium),
	Font(R.font.montserrat_bold, FontWeight.Bold)
)
val SanFranciscoFamily = FontFamily(
	Font(R.font.sf_light, FontWeight.Light),
	Font(R.font.sf_regular, FontWeight.Normal),
	Font(R.font.sf_italic, FontWeight.Normal, FontStyle.Italic),
	Font(R.font.sf_medium, FontWeight.Medium),
	Font(R.font.sf_bold, FontWeight.Bold)
)
val Typography = Typography(
	titleLarge = TextStyle(
		fontFamily = MontserratFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 26.sp
	),
	titleMedium = TextStyle(
		fontFamily = MontserratFamily,
		fontWeight = FontWeight.SemiBold,
		fontSize = 22.sp
	),
	titleSmall = TextStyle(
		fontFamily = MontserratFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 18.sp,
		brush = Brush.linearGradient(
			colors = RainBow
		)
	),
	headlineSmall = TextStyle(
		fontFamily = MontserratFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 18.sp
	),
	bodyLarge = TextStyle(
		fontFamily = SanFranciscoFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 18.sp,
		letterSpacing = 2.sp
	),
	bodyMedium = TextStyle(
		fontFamily = SanFranciscoFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
	),
	bodySmall = TextStyle(
		fontFamily = SanFranciscoFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp
	),
	labelLarge = TextStyle(
		fontFamily = SanFranciscoFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 20.sp,
	),
)