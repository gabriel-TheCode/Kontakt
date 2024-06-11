package com.gabrielthecode.kontakt.datasource.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserContactEntity(
	@PrimaryKey(autoGenerate = false)
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
)
