package com.gabrielthecode.kontakt.domain.local

import androidx.paging.PagingSource
import com.gabrielthecode.kontakt.data.database.entities.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.database.entities.UserContactEntity

interface LocalDataSource {
	fun getUserContacts(): PagingSource<Int, UserContactEntity>
	suspend fun saveKeys(remoteKeys: List<RemoteKeyEntity>)
	suspend fun saveUserContacts(userContacts: List<UserContactEntity>)
	suspend fun getRemoteKeyById(id: String): RemoteKeyEntity?
	suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>?
	suspend fun deleteAllUserContacts()
	suspend fun deleteAllRemoteKeys()
}



