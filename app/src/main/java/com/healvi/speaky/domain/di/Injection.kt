package com.healvi.speaky.domain.di

import android.content.Context
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.healvi.speaky.data.repo.FirebaseRepository
import com.healvi.speaky.data.source.local.LocalDataSourceFirebase
import com.healvi.speaky.data.source.local.room.database.UserDatabase
import com.healvi.speaky.domain.usecase.FirebaseUseCase
import com.healvi.speaky.domain.usecase.interactor.FirebaseInteractor
import com.healvi.speaky.domain.utils.AppExecutors

object Injection {
    fun firebaseRepository(context: Context): FirebaseRepository {
        val databaseUser = UserDatabase.getInstance(context)
        val localDataSource = LocalDataSourceFirebase.getInstance(databaseUser.UserDao())
        val appExecutors = AppExecutors()
        return FirebaseRepository.getInstance(Firebase.database.reference, localDataSource,appExecutors)
    }

    fun provideFirebaseUseCase(context: Context) : FirebaseUseCase {
        val repository = firebaseRepository(context)
        return FirebaseInteractor(repository)
    }
}
