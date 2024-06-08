package com.gabrielthecode.kontakt.core.local

import androidx.paging.PagingSource
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity

interface LocalDataSource {
	fun getUserContacts(): PagingSource<Int, UserEntity>
	suspend fun saveKeys(remoteKeys: List<RemoteKeyEntity>)
	suspend fun saveUserContacts(userContacts: List<UserEntity>)
	suspend fun getRemoteKeyById(id: String): RemoteKeyEntity?
	suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>?
}



