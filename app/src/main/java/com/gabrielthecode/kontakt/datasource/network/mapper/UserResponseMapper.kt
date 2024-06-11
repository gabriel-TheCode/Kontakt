package com.gabrielthecode.kontakt.datasource.network.mapper

import com.gabrielthecode.kontakt.core.domain.UserContactDomainModel
import com.gabrielthecode.kontakt.core.domain.Info
import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.datasource.network.model.UserResponse
import javax.inject.Inject

class UserResponseMapper @Inject constructor() :
	EntityMapper<UserResponse, UserDomainModel> {
	override fun mapToDomain(entity: UserResponse): UserDomainModel {
		val userItems = entity.results.map { result ->
			UserContactDomainModel(
				uuid = result.login.uuid,
				firstname = result.name.first,
				lastname = result.name.last,
				email = result.email,
				birthdate = result.dob.date,
				smallPicture = result.picture.thumbnail,
				normalPicture = result.picture.medium,
				largePicture = result.picture.large,
				phone = result.phone,
				gender = result.gender,
				street = "${result.location.street.number} ${result.location.street.name}",
				city = result.location.city,
				state = result.location.state,
				country = result.location.country,
				registered = result.registered.date
			)
		}
		val info = Info(
			page = entity.info.page,
			results = entity.info.results,
			seed = entity.info.seed,
			version = entity.info.version
		)

		return UserDomainModel(
			info = info,
			results = userItems,
		)
	}

	override fun mapToEntity(domainModel: UserDomainModel): UserResponse {
		throw NotImplementedError("Mapping from UserDomainModel to RandomUserResponse is not implemented.")
	}
}



