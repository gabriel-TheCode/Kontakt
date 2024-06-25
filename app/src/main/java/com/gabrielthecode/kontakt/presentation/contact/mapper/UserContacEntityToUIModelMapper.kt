package com.gabrielthecode.kontakt.presentation.contact.mapper

import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UserContactEntityToUIModelMapper @Inject constructor() {
	fun map(entity: UserContactEntity): UserContactUIModel {
		return UserContactUIModel(
			uuid = entity.uuid,
			firstname = entity.firstname,
			lastname = entity.lastname,
			birthdate =  entity.birthdate.formatDate(),
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
			registered = entity.registered.formatDate(),
			favorite = false
		)
	}
}

fun String.formatDate(): String {
	val dateTime = ZonedDateTime.parse(this)
	val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
	return dateTime.format(formatter)
}