package com.gabrielthecode.kontakt.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gabrielthecode.kontakt.domain.local.LocalDataSourceImpl
import com.gabrielthecode.kontakt.domain.remote.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.data.database.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.data.database.entities.UserContactEntity
import com.gabrielthecode.kontakt.data.mediator.UserContactRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserContactsRepository @Inject constructor(
	private val networkDataSource: RemoteDataSourceImpl,
	private val localDataSource: LocalDataSourceImpl,
	private val mapper: UserEntityToDomainMapper
) {
	@OptIn(ExperimentalPagingApi::class)
	fun getUserContactsFromCache(): Flow<PagingData<UserContactEntity>> {
		return Pager(
			config = PagingConfig(
				pageSize = 20
			),
			pagingSourceFactory = {
				localDataSource.getUserContacts()
			},
			remoteMediator = UserContactRemoteMediator(
				networkDataSource,
				localDataSource,
				mapper
			)
		).flow
	}
}


