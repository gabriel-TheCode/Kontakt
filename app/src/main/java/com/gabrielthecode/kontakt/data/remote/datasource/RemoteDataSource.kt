package com.gabrielthecode.kontakt.data.remote.datasource

import com.gabrielthecode.kontakt.domain.model.UserDomainModel

interface RemoteDataSource {
	suspend fun getUserContacts(
		page: Int
	): UserDomainModel
}
