package com.gabrielthecode.kontakt.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gabrielthecode.kontakt.data.local.datasource.LocalDataSourceImpl
import com.gabrielthecode.kontakt.data.remote.datasource.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.data.local.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.data.local.entities.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class UserContactRemoteMediator(
	private val networkDataSource: RemoteDataSourceImpl,
	private val localDataSource: LocalDataSourceImpl,
	private val userContactMapper: UserEntityToDomainMapper
) : RemoteMediator<Int, UserContactEntity>() {
	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, UserContactEntity>
	): MediatorResult {
		val page: Int = when (loadType) {
			LoadType.REFRESH -> {
				val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
				remoteKeys?.nextKey?.minus(1) ?: 1
			}
			LoadType.PREPEND -> {
				val remoteKeys = getRemoteKeyForFirstItem(state)
				val prevKey = remoteKeys?.prevKey
				prevKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
			}
			LoadType.APPEND -> {
				val remoteKeys = getRemoteKeyForLastItem(state)
				val nextKey = remoteKeys?.nextKey
				nextKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
			}
		}
		try {
			delay(2000) // [For testing] Simulate network delay to display infinite scroll loader
			val apiResponse = networkDataSource.getUserContacts(page = page)
			val results = apiResponse.results
			val endOfPaginationReached = results.isEmpty()

			if (loadType == LoadType.REFRESH) {
				localDataSource.deleteAllUserContacts()
				localDataSource.deleteAllRemoteKeys()
			}

			val prevKey = if (page <= 1) null else page - 1
			val nextKey = page + 1
			val remoteKeys = results.map {
				RemoteKeyEntity(
					id = it.uuid,
					currentPage = page,
					nextKey = nextKey,
					prevKey = prevKey
				)
			}
			CoroutineScope(Dispatchers.IO).launch {
				localDataSource.saveKeys(remoteKeys)
				localDataSource.saveUserContacts(results.map {
					userContactMapper.mapToEntity(it)
				})
			}

			return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (error: Exception) {
			return MediatorResult.Error(error)
		}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserContactEntity>): RemoteKeyEntity? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.uuid?.let { id ->
				localDataSource.getRemoteKeyById(id)
			}
		}
	}

	private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserContactEntity>): RemoteKeyEntity? {
		return state.pages.firstOrNull {
			it.data.isNotEmpty()
		}?.data?.firstOrNull()?.let { user ->
			localDataSource.getRemoteKeyById(user.uuid)
		}
	}

	private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserContactEntity>): RemoteKeyEntity? {
		return state.pages.lastOrNull {
			it.data.isNotEmpty()
		}?.data?.lastOrNull()?.let { user ->
			localDataSource.getRemoteKeyById(user.uuid)
		}
	}
}