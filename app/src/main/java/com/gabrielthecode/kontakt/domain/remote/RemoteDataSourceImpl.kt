package com.gabrielthecode.kontakt.domain.remote

import com.gabrielthecode.kontakt.domain.model.UserDomainModel
import com.gabrielthecode.kontakt.data.network.RandomUserApiRemoteService
import com.gabrielthecode.kontakt.data.network.mapper.UserResponseMapper
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
	private val apiService: RandomUserApiRemoteService,
	private val userResponseMapper: UserResponseMapper,
) : RemoteDataSource {
	override suspend fun getUserContacts(
		page: Int
	): UserDomainModel {
		return userResponseMapper.mapToDomain(
			apiService.getUserContacts(
				page = page
			)
		)
	}
}