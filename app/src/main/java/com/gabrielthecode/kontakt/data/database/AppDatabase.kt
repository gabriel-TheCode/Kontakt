package com.gabrielthecode.kontakt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielthecode.kontakt.data.database.dao.RemoteKeyDao
import com.gabrielthecode.kontakt.data.database.entities.RemoteKeyEntity
import com.gabrielthecode.kontakt.data.database.entities.UserContactEntity
import com.gabrielthecode.kontakt.data.database.dao.UserDao

@Database(
	entities = [UserContactEntity::class, RemoteKeyEntity::class],
	version = 1,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getUserDao(): UserDao
	abstract fun getRemoteKeyDao(): RemoteKeyDao
}
