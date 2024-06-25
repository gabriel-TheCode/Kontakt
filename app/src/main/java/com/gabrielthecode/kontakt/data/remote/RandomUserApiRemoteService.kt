package com.gabrielthecode.kontakt.data.remote

import com.gabrielthecode.kontakt.data.remote.model.UserResponse

interface RandomUserApiRemoteService {
	suspend fun getUserContacts(
		page: Int
	): UserResponse
}