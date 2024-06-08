package com.gabrielthecode.kontakt.datasource.database.mapper

import com.gabrielthecode.kontakt.core.domain.ContactDomainModel
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity
import com.gabrielthecode.kontakt.datasource.network.mapper.EntityMapper
import javax.inject.Inject

class UserEntityToDomainMapper @Inject constructor() :
	EntityMapper<UserEntity, ContactDomainModel> {
	override fun mapToDomain(entity: UserEntity): ContactDomainModel {
		entity.apply {
			return ContactDomainModel(
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

	override fun mapToEntity(domainModel: ContactDomainModel): UserEntity {
		domainModel.apply {
			return UserEntity(
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



