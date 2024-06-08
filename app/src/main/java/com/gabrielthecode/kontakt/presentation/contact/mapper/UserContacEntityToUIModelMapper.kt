package com.gabrielthecode.kontakt.presentation.contact.mapper

import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UserContactEntityToUIModelMapper @Inject constructor() {
	fun map(entity: UserEntity): UserContactUIModel {
		return UserContactUIModel(
			uuid = entity.uuid,
			firstname = entity.firstname,
			lastname = entity.lastname,
			birthdate =  formatDate(entity.birthdate),
			email = entity.email,
			smallPicture = entity.smallPicture,
			normalPicture = entity.normalPicture,
			largePicture = entity.largePicture,
			phone = entity.phone,
			gender = entity.gender,
			street = entity.street,
			city = entity.city,
			state = entity.state,
			country = entity.country,
			registered = formatDate(entity.registered),
			favorite = false // Set default value for favorite
		)
	}
}

private fun formatDate(originalDateString: String): String {
	val dateTime = ZonedDateTime.parse(originalDateString)
	val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
	return dateTime.format(formatter)
}