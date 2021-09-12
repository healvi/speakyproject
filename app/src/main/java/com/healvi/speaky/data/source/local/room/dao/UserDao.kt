package com.healvi.speaky.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.healvi.speaky.data.source.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * From User where uuid = :uuid")
    fun getUser(uuid : String): LiveData<UserEntity>

}