package com.gabrielthecode.kontakt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielthecode.kontakt.data.local.dao.RemoteKeyDao
import com.gabrielthecode.kontakt.data.local.entities.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import com.gabrielthecode.kontakt.data.local.dao.UserDao

@Database(
	entities = [UserContactEntity::class, RemoteKeyEntity::class],
	version = 1,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getUserDao(): UserDao
	abstract fun getRemoteKeyDao(): RemoteKeyDao
}
