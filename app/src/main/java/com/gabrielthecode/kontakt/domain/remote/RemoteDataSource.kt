package com.gabrielthecode.kontakt.domain.remote

import com.gabrielthecode.kontakt.domain.model.UserDomainModel

interface RemoteDataSource {
	suspend fun getUserContacts(
		page: Int
	): UserDomainModel
}
