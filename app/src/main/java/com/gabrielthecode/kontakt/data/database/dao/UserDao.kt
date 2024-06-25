package com.gabrielthecode.kontakt.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gabrielthecode.kontakt.data.database.entities.UserContactEntity

@Dao
interface UserDao {
	@Query("SELECT * FROM user WHERE uuid = :primaryId")
	fun findUserById(primaryId: String): UserContactEntity?

	@Query("DELETE FROM user WHERE uuid = :primaryId")
	suspend fun deleteUserById(primaryId: String): Int

	@Query("SELECT * FROM user")
	fun getUsers(): PagingSource<Int, UserContactEntity>

	@Query("DELETE FROM user")
	fun clearUsers()

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(userContactEntity: UserContactEntity): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg articleEntities: UserContactEntity): LongArray

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(userContactEntityList: List<UserContactEntity>): LongArray

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(userContactEntity: UserContactEntity): Int

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(vararg articleEntities: UserContactEntity): Int

	@Update(onConflict = OnConflictStrategy.REPLACE)
	fun update(userContactEntityList: List<UserContactEntity>): Int

	@Delete
	fun delete(userContactEntity: UserContactEntity): Int
}
