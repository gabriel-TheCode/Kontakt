package com.gabrielthecode.kontakt.core.usecases

import androidx.paging.PagingData
import com.gabrielthecode.kontakt.core.repositories.UserContactsRepository
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserContacts @Inject constructor(
	private val repository: UserContactsRepository,
) {
	fun getUserContacts(): Flow<PagingData<UserEntity>> {
		return repository.getUserContactsFromCache()
	}
}
