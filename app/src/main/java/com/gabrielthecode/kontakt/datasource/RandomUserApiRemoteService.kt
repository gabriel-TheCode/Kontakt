package com.gabrielthecode.kontakt.datasource

import com.gabrielthecode.kontakt.datasource.network.model.UserResponse

interface RandomUserApiRemoteService {
	suspend fun getUserContacts(
		page: Int
	): UserResponse
}