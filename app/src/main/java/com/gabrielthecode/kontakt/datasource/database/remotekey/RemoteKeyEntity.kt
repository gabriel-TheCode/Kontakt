package com.gabrielthecode.kontakt.datasource.database.remotekey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeyEntity(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	val prevKey: Int?,
	val currentPage: Int,
	val nextKey: Int?,
	val createdAt: Long = System.currentTimeMillis()
)
