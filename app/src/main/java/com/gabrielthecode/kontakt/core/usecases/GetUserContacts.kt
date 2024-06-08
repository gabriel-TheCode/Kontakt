package com.gabrielthecode.kontakt.core.usecases

import androidx.paging.PagingData
import com.gabrielthecode.kontakt.core.domain.Resource
import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.core.repositories.UserContactsRepository
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserContacts @Inject constructor(
	private val repository: UserContactsRepository,
) {
	suspend fun getUserContacts(
		page: Int
	): Flow<Resource<UserDomainModel>> = flow {
		emit(Resource.loading())
		try {
			val data = repository.getUserContacts(page)
			if (data.results.isEmpty()) {
				emit(Resource.Failure(Exception("No result found")))
			} else {
				emit(Resource.Success(data))
			}
		} catch (e: Exception) {
			emit(Resource.Failure(Exception(e.message)))
		}
	}.flowOn(Dispatchers.IO)

	fun getUserContactsWithPaging(): Flow<PagingData<UserEntity>> {
		return repository.getUserContactsFromCache()
	}
}
