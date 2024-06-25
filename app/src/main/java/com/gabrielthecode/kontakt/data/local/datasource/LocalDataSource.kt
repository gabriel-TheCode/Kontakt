package com.gabrielthecode.kontakt.data.local.datasource

import androidx.paging.PagingSource
import com.gabrielthecode.kontakt.data.local.entities.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity

interface LocalDataSource {
	fun getUserContacts(): PagingSource<Int, UserContactEntity>
	suspend fun saveKeys(remoteKeys: List<RemoteKeyEntity>)
	suspend fun saveUserContacts(userContacts: List<UserContactEntity>)
	suspend fun getRemoteKeyById(id: String): RemoteKeyEntity?
	suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>?
	suspend fun deleteAllUserContacts()
	suspend fun deleteAllRemoteKeys()
}



