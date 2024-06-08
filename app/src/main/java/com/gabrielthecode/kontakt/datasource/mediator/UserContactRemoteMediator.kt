package com.gabrielthecode.kontakt.datasource.mediator

import android.net.http.HttpException
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gabrielthecode.kontakt.core.local.LocalDataSourceImpl
import com.gabrielthecode.kontakt.core.remote.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.datasource.database.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserContactRemoteMediator(
	private val networkDataSource: RemoteDataSourceImpl,
	private val localDataSource: LocalDataSourceImpl,
	private val userContactMapper: UserEntityToDomainMapper
) : RemoteMediator<Int, UserEntity>() {
	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, UserEntity>
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
				Log.d("append", "APPEND key: $remoteKeys")
				nextKey
					?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

				/*val key = getAllRemoteKeys().lastOrNull()
				Log.d("append", "APPEND key: $key")

				key?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)*/
			}
		}
		try {
			delay(2000)
			val apiResponse = networkDataSource.getUserContacts(page = page)
			val results = apiResponse.results
			val endOfPaginationReached = results.isEmpty()
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
		} catch (error: IOException) {
			return MediatorResult.Error(error)
		} catch (error: HttpException) {
			return MediatorResult.Error(error)
		}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.uuid?.let { id ->
				localDataSource.getRemoteKeyById(id)
			}
		}
	}

	private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
		return state.pages.firstOrNull {
			it.data.isNotEmpty()
		}?.data?.firstOrNull()?.let { user ->
			localDataSource.getRemoteKeyById(user.uuid)
		}
	}

	private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserEntity>): RemoteKeyEntity? {
		return state.pages.lastOrNull {
			it.data.isNotEmpty()
		}?.data?.lastOrNull()?.let { user ->
			localDataSource.getRemoteKeyById(user.uuid)
		}
	}

	private suspend fun getAllRemoteKeys(): List<RemoteKeyEntity> {
		return localDataSource.getAllRemoteKeys()
	}
}