package com.gabrielthecode.kontakt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielthecode.kontakt.data.database.remotekey.RemoteKeyDao
import com.gabrielthecode.kontakt.data.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.database.user.UserContactEntity
import com.gabrielthecode.kontakt.data.database.user.UserDao

@Database(
	entities = [UserContactEntity::class, RemoteKeyEntity::class],
	version = 1,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getUserDao(): UserDao
	abstract fun getRemoteKeyDao(): RemoteKeyDao
}
