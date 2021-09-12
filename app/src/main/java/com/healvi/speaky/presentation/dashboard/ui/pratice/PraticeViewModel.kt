package com.healvi.speaky.presentation.dashboard.ui.pratice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.pratice.PracticeResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class PraticeViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getPratice(): LiveData<PracticeResponse> {
        return firebaseUseCase.getPractice()
    }
}