package com.gabrielthecode.kontakt.core.domain

data class UserDomainModel(
	val info: Info,
	val results: List<UserContactDomainModel>
)

data class Info(
	val page: Int,
	val results: Int,
	val seed: String,
	val version: String
)

data class UserContactDomainModel(
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
)