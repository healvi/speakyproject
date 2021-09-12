package com.healvi.speaky.domain.usecase

import androidx.lifecycle.LiveData
import com.healvi.speaky.domain.model.assesment.Assessment
import com.healvi.speaky.data.source.remote.response.assesment.AssessmentPackResponse
import com.healvi.speaky.data.source.remote.response.assesment.InstructionResponse
import com.healvi.speaky.data.source.remote.response.assesment.UserAssesmentResponse
import com.healvi.speaky.data.source.remote.response.assesment.UserResultResponse
import com.healvi.speaky.data.source.remote.response.module.BabByIdResponse
import com.healvi.speaky.data.source.remote.response.module.ModuleByIdResponse
import com.healvi.speaky.data.source.remote.response.module.ModuleResponse
import com.healvi.speaky.data.source.remote.response.module.UserModuleResponse
import com.healvi.speaky.data.source.remote.response.pratice.PracticeByIdResponse
import com.healvi.speaky.data.source.remote.response.pratice.PracticeResponse
import com.healvi.speaky.domain.model.User

interface FirebaseUseCase {
    fun getHistory(): LiveData<UserAssesmentResponse>
    fun getModule(): LiveData<ModuleResponse>
    fun getUserModule(): LiveData<UserModuleResponse>
    fun getAssessmentPack(): LiveData<AssessmentPackResponse>
    fun getPractice(): LiveData<PracticeResponse>
    fun getInstruction(id: String): LiveData<InstructionResponse>
    fun getModuleById(id: String): LiveData<ModuleByIdResponse>
    fun getPracticeById(id: String): LiveData<PracticeByIdResponse>
    fun getResult(id: String): LiveData<UserResultResponse>
    fun getBabById(id: String, moduleId: String): LiveData<BabByIdResponse>
    fun getScoreDashboard(id:String) : LiveData<User>
    fun setUser(assessment: Assessment, date: String)
}