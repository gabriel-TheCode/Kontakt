package com.gabrielthecode.kontakt.domain.usecases

import androidx.paging.PagingData
import com.gabrielthecode.kontakt.data.repositories.UserContactsRepository
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserContacts @Inject constructor(
	private val repository: UserContactsRepository,
) {
	operator fun invoke(): Flow<PagingData<UserContactEntity>> {
		return repository.getUserContactsFromCache()
	}
}
