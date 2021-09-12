package com.healvi.speaky.presentation.assessment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healvi.speaky.data.source.remote.response.assesment.AssessmentPackResponse
import com.healvi.speaky.data.source.remote.response.assesment.InstructionResponse
import com.healvi.speaky.domain.usecase.FirebaseUseCase

class AssessmentViewModel(
    private val firebaseUseCase: FirebaseUseCase
) : ViewModel() {

    private val _type = MutableLiveData<String>()
    val type: LiveData<String> = _type

    private val _instruction = MutableLiveData<String>()
    val instruction: LiveData<String> = _instruction

    fun getAssesmentPack(): LiveData<AssessmentPackResponse> {
        return firebaseUseCase.getAssessmentPack()
    }

    fun findAssessmentPack(id: String): LiveData<InstructionResponse> {
        return firebaseUseCase.getInstruction(id)
    }
}