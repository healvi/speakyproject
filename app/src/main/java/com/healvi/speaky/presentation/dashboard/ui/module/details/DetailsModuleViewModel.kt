package com.healvi.speaky.presentation.dashboard.ui.module.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.module.BabByIdResponse
import com.healvi.speaky.data.source.remote.response.module.ModuleByIdResponse
import com.healvi.speaky.data.source.remote.response.module.ModuleResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase


class DetailsModuleViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {

    fun getBab(): LiveData<ModuleResponse> {
        return firebaseUseCase.getModule()
    }

    fun setSelectedModule(moduleId: String): LiveData<ModuleByIdResponse> {
        return firebaseUseCase.getModuleById(moduleId)
    }

    fun getBabById(babId: String, moduleId: String): LiveData<BabByIdResponse> {
        return firebaseUseCase.getBabById(babId, moduleId)
    }

}