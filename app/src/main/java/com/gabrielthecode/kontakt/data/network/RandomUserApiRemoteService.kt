package com.gabrielthecode.kontakt.data.network

import com.gabrielthecode.kontakt.data.network.model.UserResponse

interface RandomUserApiRemoteService {
	suspend fun getUserContacts(
		page: Int
	): UserResponse
}