package com.gabrielthecode.kontakt.data.remote.datasource

import com.gabrielthecode.kontakt.domain.model.UserDomainModel
import com.gabrielthecode.kontakt.data.remote.RandomUserApiRemoteService
import com.gabrielthecode.kontakt.data.remote.mapper.UserResponseMapper
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