package com.gabrielthecode.kontakt.di

import android.content.Context
import androidx.room.Room
import com.gabrielthecode.kontakt.data.local.AppDatabase
import com.gabrielthecode.kontakt.data.local.dao.RemoteKeyDao
import com.gabrielthecode.kontakt.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
		return Room.databaseBuilder(
			context.applicationContext,
			AppDatabase::class.java, "kontaktapp.db"
		)
			.build()
	}

	@Singleton
	@Provides
	fun provideUserDao(database: AppDatabase): UserDao {
		return database.getUserDao()
	}

	@Singleton
	@Provides
	fun provideRemoteKeyDao(database: AppDatabase): RemoteKeyDao {
		return database.getRemoteKeyDao()
	}
}
