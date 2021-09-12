package com.healvi.speaky.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healvi.speaky.domain.model.module.UserModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.healvi.speaky.data.source.local.LocalDataSourceFirebase
import com.healvi.speaky.data.source.remote.UserDashboardResponse
import com.healvi.speaky.domain.model.assesment.AssementInstruction
import com.healvi.speaky.domain.model.assesment.Assessment
import com.healvi.speaky.domain.model.assesment.AssessmentPack
import com.healvi.speaky.domain.model.module.Bab
import com.healvi.speaky.domain.model.module.Module
import com.healvi.speaky.domain.model.pratice.Pratice
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
import com.healvi.speaky.domain.repository.IFirebaseRepository
import com.healvi.speaky.domain.utils.AppExecutors

class FirebaseRepository(rootRef: DatabaseReference, private val localDataSource: LocalDataSourceFirebase,private val appExecutors: AppExecutors) :
    IFirebaseRepository {

    private val userRef: DatabaseReference = rootRef.child("users")
    private val moduleRef: DatabaseReference = rootRef.child("ResModul")
    private val praticesRef: DatabaseReference = rootRef.child("ResPractice")
    private val userModuleRef: DatabaseReference = rootRef.child("userModul")
    private val userAssessmentRef: DatabaseReference = rootRef.child("UserAssessment")
    private val packAssessmentRef: DatabaseReference = rootRef.child("AssessmentPack")
    private var auth: FirebaseAuth = Firebase.auth
    private var uId = auth.currentUser!!.uid


    override fun getHistory(): LiveData<UserAssesmentResponse> {
        val mutableLiveData = MutableLiveData<UserAssesmentResponse>()
        userAssessmentRef.child(uId).get().addOnSuccessListener { asessement ->
            val response = UserAssesmentResponse()
            response.history = asessement.children.map {
                Assessment(
                    donwloadUrl = it.child("donwloadUrl").value.toString(),
                    score = it.child("score").value as Long,
                    timeStamp = it.child("timeStamp").value.toString(),
                    gaze = it.child("gaze").value as Long,
                    blink = it.child("blink").value as Long,
                    disfluency = it.child("disfluency").value as Long,
                )
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getModule(): LiveData<ModuleResponse> {
        val mutableLiveData = MutableLiveData<ModuleResponse>()
        moduleRef.orderByKey().get().addOnSuccessListener { mod ->
            val response = ModuleResponse()
            response.module = mod.children.map {
                Module(
                    key = it.key.toString(),
                    deskripsi = it.child("deskripsi").value.toString(),
                    gambar = it.child("gambar").value.toString(),
                    judul = it.child("judul").value.toString(),
                    bab = it.child("bab").children.map { module ->
                        Module.Bab(
                            key = module.key.toString(),
                            konten = module.child("deskripsi").value.toString(),
                            no = module.child("no").value.toString(),
                            time = module.child("time").value as Long,
                            judul = module.child("judul").value.toString(),
                            video = module.child("video").value.toString(),
                            practice = module.child("practice").children.map { prac ->
                                Module.Bab.practices(
                                    key = prac.key.toString(),
                                    time = prac.child("time").value as Long
                                )
                            }
                        )
                    }
                )
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getUserModule(): LiveData<UserModuleResponse> {
        val mutableLiveData = MutableLiveData<UserModuleResponse>()
        userModuleRef.child(uId).get().addOnSuccessListener {
            val response = UserModuleResponse()
            response.userModule = it.children.map { mod ->
                UserModule(
                    key = mod.key.toString(),
                    bab = UserModule.Bab(
                        key = mod.child("bab").child("bab1").key.toString(),
                        status = mod.child("judul").child("bab1").child("status").toString(),
                    )
                )
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getAssessmentPack(): LiveData<AssessmentPackResponse> {
        val mutableLiveData = MutableLiveData<AssessmentPackResponse>()
        packAssessmentRef.get().addOnSuccessListener {
            val response = AssessmentPackResponse()
            response.pack = it.children.map { data ->
                AssessmentPack(
                    id = data.key.toString(),
                    title = data.child("title").value.toString(),
                    type = data.child("type").value.toString(),
                    petunjuk = data.child("petunjuk").value.toString(),
                    guide = data.child("instruction").children.map {
                        data.value.toString()
                    }
                )
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getPractice(): LiveData<PracticeResponse> {
        val mutableLiveData = MutableLiveData<PracticeResponse>()
        praticesRef.get().addOnSuccessListener {
            val response = PracticeResponse()
            response.pratice = it.children.map { data ->
                Pratice(
                    key = data.key.toString(),
                    judul = data.child("judul").value.toString(),
                    cover = data.child("cover").value.toString(),
                    deskripsi = data.child("deskripsi").value.toString(),
                    ilustrasi = Pratice.Ilustration(
                        durasi = data.child("ilustrasi").child("durasi").value as Long,
                        link = data.child("ilustrasi").child("link").value.toString(),
                    )

                )
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getInstruction(id: String): LiveData<InstructionResponse> {
        val mutableLiveData = MutableLiveData<InstructionResponse>()
        packAssessmentRef.get().addOnSuccessListener {
            val response = InstructionResponse()
            response.intruction =
                AssementInstruction(
                    type = it.child(id).child("type").value.toString(),
                    intruksi = it.child(id).child("instruction").children.map { data ->
                        data.value.toString()
                    }
                )

            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getModuleById(id: String): LiveData<ModuleByIdResponse> {
        val mutableLiveData = MutableLiveData<ModuleByIdResponse>()
        moduleRef.orderByKey().get().addOnSuccessListener {
            val response = ModuleByIdResponse()
            response.module = Module(
                key = it.child(id).key.toString(),
                deskripsi = it.child(id).child("deskripsi").value.toString(),
                gambar = it.child(id).child("gambar").value.toString(),
                judul = it.child(id).child("judul").value.toString(),
                bab = it.child(id).child("bab").children.map { module ->
                    Module.Bab(
                        key = module.key.toString(),
                        no = module.child("no").value.toString(),
                        time = module.child("time").value as Long,
                        konten = module.child("deskripsi").value.toString(),
                        judul = module.child("judul").value.toString(),
                        video = module.child("video").value.toString(),
                        practice = module.child("practice").children.map { prac ->
                            Module.Bab.practices(
                                key = prac.key.toString(),
                                time = prac.child("time").value as Long
                            )
                        }
                    )
                }
            )
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getPracticeById(id: String): LiveData<PracticeByIdResponse> {
        val mutableLiveData = MutableLiveData<PracticeByIdResponse>()
        praticesRef.get().addOnSuccessListener {
            val response = PracticeByIdResponse()
            response.pratice =
                Pratice(
                    key = it.child(id).key.toString(),
                    judul = it.child(id).child("judul").value.toString(),
                    cover = it.child(id).child("cover").value.toString(),
                    deskripsi = it.child(id).child("deskripsi").value.toString(),
                    ilustrasi = Pratice.Ilustration(
                        durasi = it.child(id).child("ilustrasi").child("durasi").value as Long,
                        link = it.child(id).child("ilustrasi").child("link").value.toString(),
                    )
                )

            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getResult(id: String): LiveData<UserResultResponse> {
        val mutableLiveData = MutableLiveData<UserResultResponse>()
        userAssessmentRef.child(uId).get().addOnSuccessListener {
            val response = UserResultResponse()
            response.history = Assessment(
                donwloadUrl = it.child(id).child("donwloadUrl").value.toString(),
                score = it.child(id).child("score").value as Long,
                timeStamp = it.child(id).child("timeStamp").value.toString(),
                gaze = it.child(id).child("gaze").value as Long,
                blink = it.child(id).child("blink").value as Long,
                disfluency = it.child(id).child("disfluency").value as Long,
            )

            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun getBabById(id: String, moduleId: String): LiveData<BabByIdResponse> {
        val mutableLiveData = MutableLiveData<BabByIdResponse>()
        moduleRef.child(moduleId).child("bab").child(id).get().addOnSuccessListener { module ->
            val response = BabByIdResponse()
            response.module = Bab(
                key = module.key.toString(),
                no = module.child("no").value.toString(),
                time = module.child("time").value as Long,
                konten = module.child("deskripsi").value.toString(),
                judul = module.child("judul").value.toString(),
                video = module.child("video").value.toString(),
                practice = module.child("practice").children.map { prac ->
                    Bab.practices(
                        key = prac.key.toString(),
                        time = prac.child("time").value as Long
                    )
                }
            )
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    override fun setUser(assessment: Assessment, date: String) {
        userAssessmentRef.child(uId).child(date).setValue(assessment)
        userRef.child(uId).child("score").setValue(assessment.score)
    }

    override fun getScoreDashboard(id: String): LiveData<User> {
        val mutableLiveData = MutableLiveData<User>()
        userRef.child(uId).get().addOnSuccessListener {
            val response = User(
            name= it.child("username").value.toString(),
            username= it.child("username").value.toString(),
            email= it.child("email").value.toString(),
            imgPhoto= it.child("image").value.toString(),
            level = it.child("level").value.toString(),
            status = true,
            score = it.child("score").value as Int
            )

            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return mutableLiveData
    }

    companion object {
        @Volatile
        private var instance: FirebaseRepository? = null

        fun getInstance(
            rootRef: DatabaseReference,
            localDataSource: LocalDataSourceFirebase,
            appExecutors: AppExecutors
        ): FirebaseRepository =
            instance ?: synchronized(this) {
                FirebaseRepository(rootRef,localDataSource,appExecutors).apply { instance = this }
            }
    }
}