package com.healvi.speaky.domain.usecase.interactor

import androidx.lifecycle.LiveData
import com.healvi.speaky.data.repo.FirebaseRepository
import com.healvi.speaky.domain.model.User
import com.healvi.speaky.domain.model.assesment.Assessment
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class FirebaseInteractor(private val firebaseRepository: FirebaseRepository) : FirebaseUseCase {
    override fun getHistory() = firebaseRepository.getHistory()

    override fun getModule() = firebaseRepository.getModule()

    override fun getUserModule() = firebaseRepository.getUserModule()

    override fun getAssessmentPack() = firebaseRepository.getAssessmentPack()

    override fun getPractice() = firebaseRepository.getPractice()

    override fun getInstruction(id: String) = firebaseRepository.getInstruction(id)

    override fun getModuleById(id: String) = firebaseRepository.getModuleById(id)

    override fun getPracticeById(id: String) = firebaseRepository.getPracticeById(id)

    override fun getResult(id: String) = firebaseRepository.getResult(id)

    override fun getBabById(id: String, moduleId: String) = firebaseRepository.getBabById(id,moduleId)
    override fun getScoreDashboard(id: String): LiveData<User> {
        TODO("Not yet implemented")
    }

    override fun setUser(assessment: Assessment, date: String) = firebaseRepository.setUser(assessment,date)

}