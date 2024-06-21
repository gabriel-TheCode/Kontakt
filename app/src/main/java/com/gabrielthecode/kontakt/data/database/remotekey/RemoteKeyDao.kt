package com.gabrielthecode.kontakt.data.database.remotekey

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertKeys(keys: List<RemoteKeyEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertRemoteKey(key: RemoteKeyEntity)

	@Query("SELECT * FROM remote_key where id=:id")
	suspend fun getRemoteKeyById(id: String): RemoteKeyEntity?

	@Query("SELECT * FROM remote_key")
	suspend fun getAllRemoteKeys(): List<RemoteKeyEntity>

	@Query("DELETE FROM remote_key")
	suspend fun clearRemoteKeys()

	@Query("Select createdAt From remote_key Order By createdAt DESC LIMIT 1")
	suspend fun getCreationTime(): Long?
}
