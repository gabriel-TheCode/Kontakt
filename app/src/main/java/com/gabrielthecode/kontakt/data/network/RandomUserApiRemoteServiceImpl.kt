package com.gabrielthecode.kontakt.data.network

import com.gabrielthecode.kontakt.data.network.api.RandomUserApi
import com.gabrielthecode.kontakt.data.network.model.UserResponse

class RandomUserApiRemoteServiceImpl(
	private val randomUserApi: RandomUserApi
) : RandomUserApiRemoteService {

	override suspend fun getUserContacts(
		page: Int
	): UserResponse {
		return randomUserApi.getUserContacts(page = page)
	}
}
