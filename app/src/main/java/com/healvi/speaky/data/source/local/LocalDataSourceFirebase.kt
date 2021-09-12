package com.healvi.speaky.data.source.local

import androidx.lifecycle.LiveData
import com.healvi.speaky.data.source.local.entity.UserEntity
import com.healvi.speaky.domain.model.User
import com.healvi.speaky.data.source.local.room.dao.UserDao

class LocalDataSourceFirebase(private val userDao: UserDao) {
    companion object {
        private var INSTANCE: LocalDataSourceFirebase? = null

        fun getInstance(userDao: UserDao): LocalDataSourceFirebase =
            INSTANCE ?: LocalDataSourceFirebase(userDao)
    }

    fun insertUser(user: UserEntity) = userDao.insertUser(user)
    fun updateUser(user: UserEntity) = userDao.updateUser(user)
    fun deleteUser(user: UserEntity) = userDao.deleteUser(user)
    fun getUser(uuid : String): LiveData<UserEntity> = userDao.getUser(uuid)
}