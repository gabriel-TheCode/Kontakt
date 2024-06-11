package com.gabrielthecode.kontakt.core.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gabrielthecode.kontakt.core.local.LocalDataSourceImpl
import com.gabrielthecode.kontakt.core.remote.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.datasource.database.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.datasource.database.user.UserContactEntity
import com.gabrielthecode.kontakt.datasource.mediator.UserContactRemoteMediator
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


