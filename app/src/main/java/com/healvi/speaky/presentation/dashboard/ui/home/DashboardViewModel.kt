package com.healvi.speaky.presentation.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.domain.model.User
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class DashboardViewModel(private val firebaseUseCase: FirebaseUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getDashboardData(id:String) : LiveData<User> {
        return firebaseUseCase.getScoreDashboard(id)
    }
}