package com.gabrielthecode.kontakt.core.remote

import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.datasource.RandomUserApiRemoteService
import com.gabrielthecode.kontakt.datasource.network.mapper.UserResponseMapper
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