package com.healvi.speaky.presentation.dashboard.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.assesment.UserAssesmentResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class HistoryViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {
    fun getHistory(): LiveData<UserAssesmentResponse> {
        return firebaseUseCase.getHistory()
    }
}