package com.gabrielthecode.kontakt.core.remote

import com.gabrielthecode.kontakt.core.domain.UserDomainModel

interface RemoteDataSource {
	suspend fun getUserContacts(
		page: Int
	): UserDomainModel
}
