package com.healvi.speaky.presentation.dashboard.ui.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.module.ModuleResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class ModuleViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getModule(): LiveData<ModuleResponse> {
        return firebaseUseCase.getModule()
    }
}