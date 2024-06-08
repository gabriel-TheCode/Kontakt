package com.gabrielthecode.kontakt.datasource.database.user

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
	@Query("SELECT * FROM user WHERE uuid = :primaryId")
	fun findUserById(primaryId: String): UserEntity?

	@Query("DELETE FROM user WHERE uuid = :primaryId")
	suspend fun deleteUserById(primaryId: String): Int

	@Query("SELECT * FROM user")
	fun getUsers(): PagingSource<Int, UserEntity>

	@Query("DELETE FROM user")
	fun clear()

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(userEntity: UserEntity): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg articleEntities: UserEntity): LongArray

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(userEntityList: List<UserEntity>): LongArray

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(userEntity: UserEntity): Int

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(vararg articleEntities: UserEntity): Int

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(userEntityList: List<UserEntity>): Int

	@Delete
	fun delete(userEntity: UserEntity): Int
}
