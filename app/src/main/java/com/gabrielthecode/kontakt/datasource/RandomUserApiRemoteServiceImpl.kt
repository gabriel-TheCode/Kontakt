package com.gabrielthecode.kontakt.datasource

import com.gabrielthecode.kontakt.datasource.network.api.RandomUserApi
import com.gabrielthecode.kontakt.datasource.network.model.UserResponse

class RandomUserApiRemoteServiceImpl(
	private val randomUserApi: RandomUserApi
) : RandomUserApiRemoteService {

	override suspend fun getUserContacts(
		page: Int
	): UserResponse {
		return randomUserApi.getUserContacts(page = page)
	}
}
