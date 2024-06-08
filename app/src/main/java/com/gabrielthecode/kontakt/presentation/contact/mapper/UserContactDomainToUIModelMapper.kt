package com.gabrielthecode.kontakt.presentation.contact.mapper

import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UserContactDomainToUIModelMapper @Inject constructor() {
	fun map(domainModel: UserDomainModel): List<UserContactUIModel> {
		return domainModel.results.map { item ->
			UserContactUIModel(
				uuid = item.uuid,
				firstname = item.firstname,
				lastname = item.lastname,
				birthdate =  formatDate(item.birthdate),
				email = item.email,
				smallPicture = item.smallPicture,
				normalPicture = item.normalPicture,
				largePicture = item.largePicture,
				phone = item.phone,
				gender = item.gender,
				street = item.street,
				city = item.city,
				state = item.state,
				country = item.country,
				registered = formatDate(item.registered),
				favorite = false // Set default value for favorite
			)
		}
	}

	private fun formatDate(originalDateString: String): String {
		val dateTime = ZonedDateTime.parse(originalDateString)
		val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
		return dateTime.format(formatter)
	}
}