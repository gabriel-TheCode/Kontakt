package com.gabrielthecode.kontakt.data.local.mapper

import com.gabrielthecode.kontakt.domain.model.UserContactDomainModel
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import com.gabrielthecode.kontakt.data.remote.mapper.EntityMapper
import javax.inject.Inject

class UserEntityToDomainMapper @Inject constructor() :
	EntityMapper<UserContactEntity, UserContactDomainModel> {
	override fun mapToDomain(entity: UserContactEntity): UserContactDomainModel {
		entity.apply {
			return UserContactDomainModel(
				uuid = uuid,
				firstname = firstname,
				lastname = lastname,
				birthdate = birthdate,
				email = email,
				smallPicture = smallPicture,
				normalPicture = normalPicture,
				largePicture = largePicture,
				phone = phone,
				gender = gender,
				street = street,
				city = city,
				state = state,
				country = country,
				registered = registered
			)
		}
	}

	override fun mapToEntity(domainModel: UserContactDomainModel): UserContactEntity {
		domainModel.apply {
			return UserContactEntity(
				uuid = uuid,
				firstname = firstname,
				lastname = lastname,
				birthdate = birthdate,
				email = email,
				smallPicture = smallPicture,
				normalPicture = normalPicture,
				largePicture = largePicture,
				phone = phone,
				gender = gender,
				street = street,
				city = city,
				state = state,
				country = country,
				registered = registered,
				favorite = false
			)
		}
	}
}



