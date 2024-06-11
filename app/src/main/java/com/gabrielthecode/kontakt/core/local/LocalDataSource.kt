package com.gabrielthecode.kontakt.core.local

import androidx.paging.PagingSource
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.datasource.database.user.UserContactEntity

interface LocalDataSource {
	fun getUserContacts(): PagingSource<Int, UserContactEntity>
	suspend fun saveKeys(remoteKeys: List<RemoteKeyEntity>)
	suspend fun saveUserContacts(userContacts: List<UserContactEntity>)
	suspend fun getRemoteKeyById(id: String): RemoteKeyEntity?
	suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>?
	suspend fun deleteAllUserContacts()
	suspend fun deleteAllRemoteKeys()
}



