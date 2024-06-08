package com.gabrielthecode.kontakt.presentation.contact.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserContactUIModel(
	val uuid: String,
	val firstname: String,
	val lastname: String,
	val birthdate: String,
	val email: String,
	val smallPicture: String,
	val normalPicture: String,
	val largePicture: String,
	val phone: String,
	val gender: String,
	val street: String,
	val city: String,
	val state: String,
	val country: String,
	val registered: String,
	var favorite: Boolean = false
) : Parcelable
