package com.healvi.speaky.presentation.dashboard.ui.pratice.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.pratice.PracticeByIdResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class DetailPracticeViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {
    fun getPraticeById(id: String): LiveData<PracticeByIdResponse> {
        return firebaseUseCase.getPracticeById(id)
    }
}