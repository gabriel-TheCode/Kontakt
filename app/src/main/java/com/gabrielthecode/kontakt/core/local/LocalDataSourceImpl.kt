package com.gabrielthecode.kontakt.core.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.gabrielthecode.kontakt.datasource.database.AppDatabase
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.datasource.database.user.UserContactEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
	private val database: AppDatabase
) : LocalDataSource {
	override fun getUserContacts(): PagingSource<Int, UserContactEntity> {
		return database.getUserDao().getUsers()
	}

	override suspend fun saveKeys(remoteKeys: List<RemoteKeyEntity>) {
		database.getRemoteKeyDao().insertKeys(remoteKeys)
	}

	override suspend fun saveUserContacts(userContacts: List<UserContactEntity>) {
		database.getUserDao().insert(userContacts)
	}

	override suspend fun getRemoteKeyById(id: String): RemoteKeyEntity? {
		return database.getRemoteKeyDao().getRemoteKeyById(id)
	}

	override suspend fun getAllRemoteKeys(): List<RemoteKeyEntity> {
		return database.getRemoteKeyDao().getAllRemoteKeys()
	}

	override suspend fun deleteAllUserContacts() {
		database.withTransaction {
			database.getUserDao().clearUsers()
		}
	}

	override suspend fun deleteAllRemoteKeys() {
		database.withTransaction {
			database.getRemoteKeyDao().clearRemoteKeys()
		}
	}
}
