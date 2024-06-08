package com.gabrielthecode.kontakt.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyDao
import com.gabrielthecode.kontakt.datasource.database.remotekey.RemoteKeyEntity
import com.gabrielthecode.kontakt.datasource.database.user.UserDao
import com.gabrielthecode.kontakt.datasource.database.user.UserEntity

@Database(entities = [UserEntity::class, RemoteKeyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
	abstract fun getUserDao(): UserDao
	abstract fun getRemoteKeyDao(): RemoteKeyDao

	companion object {
		/** The only instance  */
		private var sInstance: AppDatabase? = null

		/**
		 * Gets the singleton instance of AppDatabase.
		 *
		 * @param context The context.
		 * @return The singleton instance of AppDatabase.
		 */
		@Synchronized
		fun getInstance(context: Context): AppDatabase {
			if (sInstance == null) {
				sInstance = Room
					.databaseBuilder(
						context.applicationContext,
						AppDatabase::class.java, "kontaktapp.db"
					)
					.build()
			}
			return sInstance as AppDatabase
		}
	}
}
