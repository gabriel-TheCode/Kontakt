package com.gabrielthecode.kontakt.data.remote

import com.gabrielthecode.kontakt.data.remote.api.RandomUserApi
import com.gabrielthecode.kontakt.data.remote.model.UserResponse

class RandomUserApiRemoteServiceImpl(
	private val randomUserApi: RandomUserApi
) : RandomUserApiRemoteService {

	override suspend fun getUserContacts(
		page: Int
	): UserResponse {
		return randomUserApi.getUserContacts(page = page)
	}
}
